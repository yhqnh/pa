package com.ikamobile.pa.service.impl;

import com.alibaba.fastjson.JSON;
import com.ikamobile.pa.common.enums.BusinessExceptionCodeEnum;
import com.ikamobile.pa.common.enums.CertificateTypeEnum;
import com.ikamobile.pa.common.enums.EntityTypeEnum;
import com.ikamobile.pa.common.enums.OperateLogTargetType;
import com.ikamobile.pa.common.exception.BusinessException;
import com.ikamobile.pa.common.utils.DateTimeUtils;
import com.ikamobile.pa.common.utils.ListUtils;
import com.ikamobile.pa.common.utils.StringUtils;
import com.ikamobile.pa.dao.*;
import com.ikamobile.pa.dao.model.*;
import com.ikamobile.pa.dao.param.UpdateByIdParam;
import com.ikamobile.pa.dao.query.Criteria;
import com.ikamobile.pa.dao.query.CriteriaQuery;
import com.ikamobile.pa.dao.query.Pager;
import com.ikamobile.pa.service.*;
import com.ikamobile.pa.thrift.common.*;
import com.ikamobile.pa.thrift.server.acceptor.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by gjx
 */
@Slf4j
@Service
@Transactional(rollbackFor = {TBusinessException.class,RuntimeException.class})
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private PassengerDao passengerDao;
    @Resource
    private UserDao userDao;
    @Resource
    private AreaDao areaDao;

    @Resource
    private TaskDao taskDao;

    @Resource
    private DispatcherDao dispatcherDao;


    @Resource
    private VehicleAndDriverService vehicleAndDriverService;

    @Resource
    private OperateLogService operateLogService;

    @Resource
    private TaskService taskService;

    @Resource
    private SmsService smsService;

    @Override
    public OrderDetailDto create(OrderCreateParam orderCreateParam) throws TBusinessException, TException {
        log.info("create order with the param==>{}",JSON.toJSONString(orderCreateParam));
        Order order = new Order();
        order.setStatus(Order.OrderStatus.unconfirmed);
        order.setCode(StringUtils.genCode(EntityTypeEnum.ORDER));
        order.setOnline(orderCreateParam.isOnline);
        String operatorName = "";
        if(!orderCreateParam.isIsOnline()){
            Dispatcher dispatcher = getDispatcher(orderCreateParam.getOperatorId());
            operatorName = "客服（"+dispatcher.getLoginName()+"）";
        } else {
            operatorName = "预订人（"+orderCreateParam.getMobile()+"）";
        }

        //1.设置预订人信息
        CriteriaQuery query = new CriteriaQuery();
        query.or().andEqualTo(User.FIELD_PHONE_NUMBER,orderCreateParam.getMobile());
        List<User> users = userDao.selectByCriteriaQuery(query);
        if(users.size() > 0){
            order.setBookerId(users.get(0).getId());
        } else {
            //创建用户

            User user = new User();
            user.setPhoneNumber(orderCreateParam.getMobile());
            userDao.insertSelective(user);
            log.info("create user finished ,the userId=>{}",user.getId());
            order.setBookerId(user.getId());
        }


        //2.上车地点
        if(Objects.isNull(orderCreateParam.getAboardPosition()) ||
                Objects.isNull(orderCreateParam.getAboardPosition().getArea())){
            log.warn("the need param is null,check the param");
            throw new TBusinessException(1,"上车地点及所在区域不能为空");
        }

        order.setAboardAreaId(orderCreateParam.getAboardPosition().getArea().getId());
        order.setAboardPlace(orderCreateParam.getAboardPosition().getPositionDesc());


        //抵达地点
        order.setArrPlace(Airports.CTU_AIRPORT_NAME);
        order.setArrAirportId(Airports.CTU_AIRPORT_ID);


        //航班信息
        order.setFlightNumber(orderCreateParam.getFlightNumber());
        if(orderCreateParam.getFlightDepTime() > 0){
            DateTime dateTime = new DateTime();
            dateTime = dateTime.plusHours(2);
            // todo 修改时间策略
            if(dateTime.toDate().getTime() > orderCreateParam.getFlightDepTime()){
                throw new TBusinessException(1,"距离航班起飞时间已经不足两小时，无法进行预约");
            }
            order.setFlightDepTime(new Date(orderCreateParam.getFlightDepTime()));

        } else {
            throw new TBusinessException(1,"航班起飞时间不能为空");

        }
        //铁航接送单号
        order.setVoucherCode(orderCreateParam.getVoucherCode());

        if(orderCreateParam.getPassengers().size() <= 0){
            throw new TBusinessException(1,"还没有添加乘客");
        }
        //保存订单信息
        order.setPassengerNum(orderCreateParam.getPassengers().size());
        orderDao.insertSelective(order);

        //设置乘客信息
        for( PassengerDto passengerDto : orderCreateParam.getPassengers()){
            Passenger passenger = new Passenger();
            passenger.setName(passengerDto.getName());
            passenger.setCertificateType(CertificateTypeEnum.valueOf(passengerDto.getCertificateType()));
            passenger.setCertificateNumber(passengerDto.getCertificateNumber());
            passenger.setOrderId(order.getId());
            passengerDao.insertSelective(passenger);
        }



        //添加日志
        operateLogService.orderFront(order.getId(),"创建订单成功",operatorName);

        return orderDetail(order.getId());

    }


    @Override
    @Transactional(readOnly = true)
    public OrderPageResponse listOrder(OrderQueryParam orderQueryPram) throws TBusinessException, TException {

        log.info("订单查询参数：：{}", JSON.toJSONString(orderQueryPram));
        OrderPageResponse response = new OrderPageResponse();
        //先查询订单

        CriteriaQuery orderQuery = new CriteriaQuery();
        Criteria orderCriteria = orderQuery.or();

        //搜索条件
        if(Objects.nonNull(orderQueryPram.getSearchParam())){
            OrderSearchParam searchParam = orderQueryPram.getSearchParam();

            //预订人信息
            if(StringUtils.isNotBlank(searchParam.getBookerMobile())){
                CriteriaQuery userQuery = new CriteriaQuery();
                userQuery.or().andEqualTo(User.FIELD_PHONE_NUMBER,searchParam.getBookerMobile());
                List<User> users = userDao.selectByCriteriaQuery(userQuery);
                if(users.size() > 0){
                    orderCriteria.andEqualTo(Order.FIELD_BOOKER_ID,users.get(0).getId());
                } else {
                    response.setPageContent(new ArrayList<>());
                    response.setPagerInfo(new PagerInfoDto(0,0,0,0));
                    return response;
                }
            }

            // code条件
            if(StringUtils.isNotBlank(searchParam.getCode())){
                orderCriteria.andEqualTo(Order.FIELD_CODE,searchParam.getCode());
            }

            // status条件
            if(!ListUtils.isNullOrEmpty(searchParam.getStatus())){
                orderCriteria.andIn(Order.FIELD_STATUS,searchParam.getStatus());// to check
            }

            //航班号条件
            if(StringUtils.isNotBlank(searchParam.getFlightNumber())){
                orderCriteria.andEqualTo(Order.FIELD_FLIGHT_NUMBER,searchParam.getFlightNumber());
            }
            //航班起飞时间
            if(searchParam.getFlightDepTimeFloor() > 0){
                orderCriteria.andGreaterThanOrEqualTo(Order.FIELD_FLIGHT_DEP_TIME,
                        DateTimeUtils.convertTimeToString(new Date(searchParam.getFlightDepTimeFloor())));
            }
            if(searchParam.getFlightDepTimeTop() > 0){
                orderCriteria.andLessThanOrEqualTo(Order.FIELD_FLIGHT_DEP_TIME,
                        DateTimeUtils.convertTimeToString(new Date(searchParam.getFlightDepTimeTop())));
            }
            //车辆自编号查询
            if(StringUtils.isNotBlank(searchParam.getVehicleCode())){
                orderCriteria.andEqualTo(Order.FIELD_VEHICLE_CODE,searchParam.getVehicleCode());
            }

            //操作人id查询
            if(StringUtils.isNotBlank(searchParam.getOperatorId())){
                orderCriteria.andEqualTo(Order.FIELD_OPERATOR_ID,searchParam.getOperatorId());
            }

            //是否是线上单
            if(StringUtils.isNotBlank(searchParam.getForOnline())){
                if("Y".equalsIgnoreCase(searchParam.getForOnline())){
                    orderCriteria.andEqualTo(Order.FIELD_ONLINE,true);
                } else if("N".equalsIgnoreCase(searchParam.getForOnline())){
                    orderCriteria.andEqualTo(Order.FIELD_ONLINE,false);
                }
                //other do nothing
            }

            //铁航接送单号
            if(StringUtils.isNotBlank(searchParam.getVoucherCode())){
                orderCriteria.andEqualTo(Order.FIELD_VOUCHER_CODE,searchParam.getVoucherCode());
            }
            //创建时间上下限
            if(searchParam.getCreateTimeFloor() > 0){
                orderCriteria.andGreaterThanOrEqualTo(Order.FIELD_CREATE_TIME,
                        DateTimeUtils.convertTimeToString(new Date(searchParam.getCreateTimeFloor())));
            }
            if(searchParam.getCreateTimeTop() > 0){
                orderCriteria.andLessThanOrEqualTo(Order.FIELD_CREATE_TIME,
                        DateTimeUtils.convertTimeToString(new Date(searchParam.getCreateTimeTop())));
            }

            log.info("the search param ===>{}",JSON.toJSONString(orderCriteria));
        }

        //分页信息

        //查询总条数
        Long aLong = orderDao.countByCriteriaQuery(orderQuery);
        //计算总条数
        if(aLong != null){
            PagerInfoDto pagerInfoDto = new PagerInfoDto();
            pagerInfoDto.setTotalRowNum(aLong.intValue());
            //判断是否有page信
            if(orderQueryPram.getPager() != null){
                int totalPageNum;
                if(aLong % orderQueryPram.getPager().getPageSize() == 0){
                    totalPageNum = (int) (aLong/orderQueryPram.getPager().getPageSize());
                } else {
                    totalPageNum = (int) (aLong/orderQueryPram.getPager().getPageSize()) + 1;
                }
                pagerInfoDto.setTotalPageNum(totalPageNum);
                pagerInfoDto.setPageSize(orderQueryPram.getPager().getPageSize());
                int pageIndex = orderQueryPram.getPager().getPageIndex() == 0 ? 1:orderQueryPram.getPager().getPageIndex();
                pagerInfoDto.setPageIndex(pageIndex);
            }
            response.setPagerInfo(pagerInfoDto);
        }

        //设置page信息 进行查询
        PagerDto pagerDto = orderQueryPram.getPager();
        if(pagerDto != null){
            orderQuery.setPager(new Pager(pagerDto.pageIndex,pagerDto.getPageSize()));
        }

        //排序信息

        //添加排序信息
        if(Objects.nonNull(orderQueryPram.getSorter())){
            OrderQuerySorterEnum orderQuerySorter = orderQueryPram.getSorter();
            List<CriteriaQuery.Sort> sorts = new ArrayList<>();

            if(Objects.nonNull(orderQuerySorter.getCreateTime())){
                CriteriaQuery.Sort.Direction direction = CriteriaQuery.Sort.Direction.DESC;
                if(Sorter.ASC.equals(orderQuerySorter.getCreateTime())){
                    direction = CriteriaQuery.Sort.Direction.ASC;
                }
                CriteriaQuery.Sort sort = new CriteriaQuery.Sort(Order.FIELD_CREATE_TIME, direction);
                sorts.add(sort);
            }
            if(Objects.nonNull(orderQuerySorter.getFlightDepTime())){
                CriteriaQuery.Sort.Direction direction = CriteriaQuery.Sort.Direction.DESC;
                if(Sorter.ASC.equals(orderQuerySorter.getFlightDepTime())){
                    direction = CriteriaQuery.Sort.Direction.ASC;
                }
                CriteriaQuery.Sort sort = new CriteriaQuery.Sort(Order.FIELD_FLIGHT_DEP_TIME, direction);
                sorts.add(sort);
            }
            if(Objects.nonNull(orderQuerySorter.getAreaId())){
                CriteriaQuery.Sort.Direction direction = CriteriaQuery.Sort.Direction.DESC;
                if(Sorter.ASC.equals(orderQuerySorter.getAreaId())){
                    direction = CriteriaQuery.Sort.Direction.ASC;
                }
                CriteriaQuery.Sort sort = new CriteriaQuery.Sort(Order.FIELD_ABOARD_AREA_ID, direction);
                sorts.add(sort);
            }

            orderQuery.setSorts(sorts);
        }


        //查询订单

        log.info("the order query obj===>{}",JSON.toJSONString(orderQuery));

        List<Order> orders = orderDao.selectByCriteriaQuery(orderQuery);
        if(ListUtils.isNullOrEmpty(orders)){
            response.setPageContent(new ArrayList<>());
            return response;
        }
        //拼装订单
        List<OrderDetailDto> detailDtoList = new ArrayList<>();
        for(Order order:orders){
            OrderDetailDto dto = buildOrderDetail(order);
            detailDtoList.add(dto);
        }
        response.setPageContent(detailDtoList);

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDetailDto orderDetail(String orderId) throws TBusinessException, TException {
        Order order = this.getOrderById(orderId);
        if(Objects.isNull(order)){
            log.info("the order with id=[{}]  do not exist ",orderId);
            throw new TBusinessException(1,"订单不存在");
        }
        OrderDetailDto dto = buildOrderDetail(order);
        return dto;

    }

    @Override
    public OperateResponse cancelOrder(String orderId, String mobile) throws TBusinessException, TException {
        //查询订单

        Order order = orderDao.selectById(orderId);
        if(Objects.isNull(order)){
            throw new TBusinessException(1,"订单不存在");
        }
        User user = userDao.selectById(order.getBookerId());

        if(Objects.isNull(user) || !user.getPhoneNumber().equals(mobile)){
            throw new TBusinessException(1,"无权限操作该订单");
        }
        //校验是否被锁定
        if(StringUtils.isNotBlank(order.getLockKey())){
            throw new TBusinessException(1,"客服正在操作，暂时无法取消");
        }

        checkStatusForCancel(order);


        //更新
        order.setStatus(Order.OrderStatus.cancelled);
        order.setUpdateTime(null);//将更新时间置为空，使得数据库自动更新该字段
        UpdateByIdParam updateParam = new UpdateByIdParam(orderId,order);
        orderDao.updateByIdSelective(updateParam);

        // 记录日志
        operateLogService.orderFront(orderId,"取消订单成功","预订人（"+mobile+")");
        return new OperateResponse(OperateCode.success,"取消成功");
    }

    private void checkStatusForCancel(Order order) throws TBusinessException {
        //以下状态可以取消订单
        // unconfirmed(1),//待确认
        //confirmed(2),//已确认
        if(!Order.OrderStatus.unconfirmed.equals(order.getStatus())
                &&!Order.OrderStatus.confirmed.equals(order.getStatus())
                ){
            if(Order.OrderStatus.cancelled.equals(order.getStatus())){
                throw new TBusinessException(1,"订单已取消，无需重复操作");
            }
            throw new TBusinessException(1,"该状态无法取消订单");
        }
    }

    @Override
    public OperateResponse dispactherCancelOrder(String orderId, String operatorId) throws TBusinessException, TException {

        Order order = orderDao.selectById(orderId);
        if(Objects.isNull(order)){
            throw new TBusinessException(1,"订单不存在");
        }


        //校验是否被锁定
        checkTheLock(order,operatorId);

        //  订单状态校验
        checkStatusForCancel(order);
        // 保存操作人
        Dispatcher dispatcher = dispatcherDao.selectById(operatorId);
        if(Objects.isNull(dispatcher)){
            throw new TBusinessException(1,"调度人员不存在");
        }


        //更新
        order.setStatus(Order.OrderStatus.cancelled);
//        order.setOperatorId(operatorId);
        order.setUpdateTime(null);//将更新时间置为空，使得数据库自动更新该字段
        UpdateByIdParam updateParam = new UpdateByIdParam(orderId,order);
        orderDao.updateByIdSelective(updateParam);
        //  记录日志

        operateLogService.orderFront(orderId,"取消订单成功",dispatcher.getLoginName());
        clearLock(orderId);
        return new OperateResponse(OperateCode.success,"取消成功");
    }

    @Override
    public OperateResponse driverConfirmPassengerAboarded(String orderId, String driverId) throws TBusinessException, TException {


        if(StringUtils.isNullOrEmpty(driverId)){
            throw new TException("driverId 不能为空");
        }
        Order order = orderDao.selectById(orderId);
        if(order == null){
            throw new TBusinessException(BusinessExceptionCodeEnum.ORDER_NOT_EXISTS.getCode(),
                    BusinessExceptionCodeEnum.ORDER_NOT_EXISTS.getMessage());
        }
        //状态判断
        if(!Order.OrderStatus.waiting.equals(order.getStatus())){
            throw new TBusinessException(BusinessExceptionCodeEnum.ORDER_STATUS_EX.getCode()
                    , BusinessExceptionCodeEnum.ORDER_STATUS_EX.getMessage());
        }
        //判断司机身份是否符合
        Task task = taskDao.selectById(order.getTaskId());

        if(StringUtils.isNullOrEmpty(task.getDriverId()) ||  !driverId.equals(task.getDriverId())){
            throw new TBusinessException(1,"任务、司机信息不匹配");
        }


        order.setStatus(Order.OrderStatus.picked);
        order.setUpdateTime(null);//将更新时间置为空，使得数据库自动更新该字段
        UpdateByIdParam updateParam = new UpdateByIdParam(orderId,order);
        orderDao.updateByIdSelective(updateParam);
        //记录日志
        Driver driver = vehicleAndDriverService.getDriverById(driverId);
        operateLogService.orderFront(orderId,"司机确认乘客上车",driver.getName());
        return new OperateResponse(OperateCode.success,"操作成功");
    }

    @Override
    public OrderDetailDto buildOrderDetail(Order order) {

        //设置预定信息
        if(order == null){
            return null;
        }
        OrderDetailDto dto = new OrderDetailDto();
        copyToDto(order, dto);

        //设置预订人信息
        if(StringUtils.isNotBlank(order.getBookerId())){
            dto.setBooker(getUserById(order.getBookerId()));
        }
        // 设置乘客信息
        dto.setPassengers(getPassengerByOrderId(order.getId()));
        // 设置位置上车下车位信息
        dto.setAboardPosition(getAboardPositon(order));
        dto.setArrPosition(getArrPosition(order));

        if(StringUtils.isNotBlank(order.getTaskId())){
            Task task = taskDao.selectById(order.getTaskId());
            //获取司机信息
            dto.setDriver(vehicleAndDriverService.getDriverDtoById(task.getDriverId()));
            //获取车辆信息
            dto.setVehicle(vehicleAndDriverService.getVehicleDtoById(task.getVehicleId()));
        }
        //获取最后一个操作人信息,
        if(StringUtils.isNotBlank(order.getOperatorId())){
            Dispatcher dispatcher = dispatcherDao.selectById(order.getOperatorId());
            if(Objects.nonNull(dispatcher)){
                dto.setOperatorName(dispatcher.getLoginName());
            }
        }
        //获取日志信息
        dto.setOperateLogs(operateLogService.listOperateLogs(OperateLogTargetType.ORDER,order.getId(), null));
        return dto;
    }


    private void copyToDto(Order order, OrderDetailDto dto) {
        dto.setId(order.getId());

        dto.setCode(order.getCode());
        dto.setStatus(order.getStatus().name());
        dto.setFlightNumber(order.getFlightNumber());
        if(Objects.nonNull(order.getFlightDepTime())){
            dto.setFlightDepTime(order.getFlightDepTime().getTime());
        }
        dto.setOnline(order.getOnline());
        dto.setVoucherCode(order.getVoucherCode());
        if(Objects.nonNull(order.getExpectBoardTime())){
            dto.setExpectBoardTime(order.getExpectBoardTime().getTime());
        }
        if(order.getActualBoardTime() != null){
            dto.setActualBoardTime(order.getActualBoardTime().getTime());
        }
        if(Objects.nonNull(order.getArrTime())){
            dto.setArrTime(order.getArrTime().getTime());
        }
        if(Objects.nonNull(order.getCreateTime())){
            dto.setCreateTime(order.getCreateTime().getTime());
        }

    }

    private PositionDto getArrPosition(Order order) {
        PositionDto positionDto = new PositionDto();
        positionDto.setArea(null);
        positionDto.setPositionDesc(order.getArrPlace());
        return positionDto;
    }

    private PositionDto getAboardPositon(Order order) {


        PositionDto positionDto = new PositionDto();
        String aboardAreaId = order.getAboardAreaId();
        if(StringUtils.isNotBlank(aboardAreaId)){
            Area area = areaDao.selectById(aboardAreaId);
            positionDto.setArea(convertToArea(area));
        }
        positionDto.setPositionDesc(order.getAboardPlace());
        return positionDto;

    }

    private AreaDto convertToArea(Area area) {
        if(Objects.isNull(area)){
            return null;
        }
        AreaDto dto = new AreaDto();
        dto.setId(area.getId());
        dto.setCode(area.getCode());
        dto.setName(area.getName());
        return dto;
    }

    private List<PassengerDto> getPassengerByOrderId(String id) {
        CriteriaQuery query = new CriteriaQuery();
        query.or().andEqualTo(Passenger.FIELD_ORDER_ID,id);
        List<Passenger> passengers = passengerDao.selectByCriteriaQuery(query);
        if(ListUtils.isNullOrEmpty(passengers)){
            return new ArrayList<>();
        }
        List<PassengerDto> passengerDtos = new ArrayList<>();

        for(Passenger passenger : passengers){
            PassengerDto passengerDto = convertToPassengerDto(passenger);
            passengerDtos.add(passengerDto);
        }

        return passengerDtos;

    }

    private PassengerDto convertToPassengerDto(Passenger passenger) {
        if(passenger == null){
            return null;
        }
        PassengerDto dto = new PassengerDto();
        dto.setId(passenger.getId());
        dto.setName(passenger.getName());
        dto.setCertificateType(passenger.getCertificateType().name());
        dto.setCertificateNumber(passenger.getCertificateNumber());
        return dto;
    }

    /**
     * 根据uuid 获取userDTO
     * @param uuid
     * @return
     */
    private UserDto getUserById(String uuid){
        User user = userDao.selectById(uuid);
        if(user == null){
            return null;
        }
        UserDto userDto = convertToUserDto(user);
        return userDto;
    }

    private UserDto convertToUserDto(User user){
        if(user == null){
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setWeixinOpenId(user.getWeixinOpenId());
        userDto.setDeviceNo(user.getDeviceNo());
        return userDto;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Order> getByTaskId(String taskId) {
        CriteriaQuery query = new CriteriaQuery();
        //添加排序信息，以上车时间进行排序
        List<CriteriaQuery.Sort> sorts = new ArrayList<>();
        sorts.add(new CriteriaQuery.Sort(Order.FIELD_EXPECT_BOARD_TIME, CriteriaQuery.Sort.Direction.ASC));
        query.setSorts(sorts);
        query.or().andEqualTo(Order.FIELD_TASK_ID,taskId);
        List<Order> orders = orderDao.selectByCriteriaQuery(query);
        return orders;
    }

    @Override
    public void changeToConfirmed(Order order) {

        if(StringUtils.isNullOrEmpty(order.getId())){
            throw new BusinessException("order's id can not be null");
        }

        order.setStatus(Order.OrderStatus.confirmed);
        order.setUpdateTime(null);
        UpdateByIdParam param = new UpdateByIdParam(order.getId(),order);
        orderDao.updateByIdSelective(param);
    }

    @Override
    public void updateToTask(Order order, Task task) {
        if(StringUtils.isNullOrEmpty(order.getId())){
            throw new BusinessException("order's id can not be null");
        }
        if(StringUtils.isNullOrEmpty(task.getId())){
            throw new BusinessException("task's id can not be null");
        }
        order.setTaskId(task.getId());
        UpdateByIdParam param = new UpdateByIdParam(order.getId(),order);
        orderDao.updateByIdSelective(param);
    }

    @Override
    //task发车时，订单状态修改
    public void changeAllToWaiting(Task task,Dispatcher dispatcher) throws TBusinessException{
        CriteriaQuery query = new CriteriaQuery();
        query.or().andEqualTo(Order.FIELD_TASK_ID,task.getId());
        List<Order> orders = orderDao.selectByCriteriaQuery(query);
        if(ListUtils.isNullOrEmpty(orders)){
            return;
        }


        Driver driver = vehicleAndDriverService.getDriverById(task.getDriverId());
        for(Order order:orders){
            if(!Order.OrderStatus.planned.equals(order.getStatus())){
                throw new TBusinessException(1,"订单状态不为【已派车】");
            }
            //判断时间
            if(new Date().after(order.getExpectBoardTime())){
                throw new TBusinessException(1,"已过预计发车时间");
            }
            Order updateTemp = new Order();
            updateTemp.setStatus(Order.OrderStatus.waiting);
            updateTemp.setOperatorId(dispatcher.getId());
            UpdateByIdParam param = new UpdateByIdParam(order.getId(),updateTemp);
            orderDao.updateByIdSelective(param);
            User user = userDao.selectById(order.getBookerId());
            //日志记录
            operateLogService.orderBack(order.getId(),"司机准备发车",dispatcher.getLoginName());
            smsService.sendNotifyWhenOrderStart(user.getPhoneNumber(),order,driver);
        }

    }


    @Override
    public void changeAllToServed(Task task,Driver driver) {
        CriteriaQuery query = new CriteriaQuery();
        query.or().andEqualTo(Order.FIELD_TASK_ID, task.getId());
        List<Order> orders = orderDao.selectByCriteriaQuery(query);
        if(ListUtils.isNullOrEmpty(orders)){
            return;
        }
        for(Order order:orders){
            if(!Order.OrderStatus.picked.equals(order.getStatus())){
                continue;
            }
            order.setStatus(Order.OrderStatus.served);
            UpdateByIdParam param = new UpdateByIdParam(order.getId(),order);
            orderDao.updateByIdSelective(param);
            operateLogService.orderFront(order.getId(),"已送达",driver.getName());
        }

    }

    @Override
    public Order getOrderById(String uuid) {
        if(StringUtils.isNullOrEmpty(uuid)){
            return null;
        }
        return orderDao.selectById(uuid);
    }

    @Override
    public OperateResponse accept(String orderId,String operatorId) throws TBusinessException, TException {


        //更改订单状态
        Order order = orderDao.selectById(orderId);
        if(Objects.isNull(order)){
            throw new TBusinessException(BusinessExceptionCodeEnum.ORDER_NOT_EXISTS.getCode(),"订单不存在");
        }
        //校验状态
        if(!Order.OrderStatus.unconfirmed.equals(order.getStatus())){
            log.warn("accept order ,the status is =====================>"+order.getStatus());
            throw new TBusinessException(BusinessExceptionCodeEnum.ORDER_STATUS_EX.getCode(),"订单状态不正确");

        }

        //检查操作人是否拥有操作该订单的锁,由于现在锁的机制直接用分单客户的id作为key，因此用操作人id去比较
        checkTheLock(order,operatorId);


        Order updateTemp = new Order();

        updateTemp.setStatus(Order.OrderStatus.confirmed);
        //获取操作人信息
        Dispatcher dispatcher = getDispatcher(operatorId);
//        updateTemp.setOperatorId(dispatcher.getId());

        //更新到数据库
        UpdateByIdParam updateByIdParam = new UpdateByIdParam(order.getId(),updateTemp);
        orderDao.updateByIdSelective(updateByIdParam);
        //清除锁
        clearLock(order.getId());

        //日志记录
        operateLogService.orderFront(orderId,"订单已确认",dispatcher.getLoginName());

        return new OperateResponse();
    }

    private Dispatcher getDispatcher(String operatorId) throws TException {
        if(StringUtils.isNullOrEmpty(operatorId)){
            throw new TBusinessException(1,"operatorId can't not be empty");
        }
        Dispatcher dispatcher = dispatcherDao.selectById(operatorId);
        if(Objects.isNull(dispatcher)){
            throw new TBusinessException(401,"未找到操作人");
        }
        return dispatcher;
    }

    /**
     * 订单锁检查，
     * 在订单已下操作会对订单进行锁检查
     * 1，订单确认 accept
     * 2，订单拒绝 deny
     * 3, 订单派车 dispatch
     * 4, 订单从人物中移除 undispatch
     * @param order
     * @param lockKey
     * @throws TBusinessException
     */
    private void checkTheLock(Order order,String lockKey) throws TBusinessException{
        if(StringUtils.isNotBlank(order.getLockKey()) && !order.getLockKey().equals(lockKey)){
            Dispatcher dispatcher = dispatcherDao.selectById(order.getLockKey());
            throw new TBusinessException(1,"该订单被【"+dispatcher.getLoginName()+"】锁定，您暂时不能进行操作");
        }
    }

    @Override
    public OperateResponse deny(String orderId,String operatorId) throws TBusinessException, TException {
        //更改订单状态
        if(StringUtils.isNullOrEmpty(orderId)){
            throw new TException("orderId can't not be empty");
        }
        Order order = orderDao.selectById(orderId);
        if(Objects.isNull(order)){
            throw new TBusinessException(BusinessExceptionCodeEnum.ORDER_NOT_EXISTS.getCode(),"订单不存在");
        }
        //校验状态
        if(!Order.OrderStatus.unconfirmed.equals(order.getStatus())){
            throw new TBusinessException(BusinessExceptionCodeEnum.ORDER_STATUS_EX.getCode(),"订单状态不正确");
        }

        //检查操作人是否拥有操作该订单的锁,由于现在锁的机制直接用分单客户的id作为key，因此用操作人id去比较
        checkTheLock(order,operatorId);


        Order updateTemp = new Order();


        updateTemp.setStatus(Order.OrderStatus.refused);
        //获取操作人信息
        if(StringUtils.isNullOrEmpty(operatorId)){
            throw new TException("operatorId can't be emtpy");
        }

        Dispatcher dispatcher = getDispatcher(operatorId);
        if(Objects.isNull(dispatcher)){
            throw new TException("操作人不存在");
        }
//        updateTemp.setOperatorId(dispatcher.getId());

        //更新到数据库
        UpdateByIdParam updateByIdParam = new UpdateByIdParam(order.getId(),updateTemp);
        orderDao.updateByIdSelective(updateByIdParam);
        //清除锁
        clearLock(order.getId());
        //日志记录
        operateLogService.orderFront(orderId,"你的订单不符合派车要求，已被拒绝",dispatcher.getLoginName());
        return new OperateResponse();
    }

    @Override
    public OperateResponse dispatch(DispatchParamDto dispatchParamDto,String operatorId) throws TBusinessException, TException {
        log.info("orderserivce dispatch with the param===>{}",JSON.toJSONString(dispatchParamDto));
        String orderId = dispatchParamDto.getOrderId();
        if(StringUtils.isNullOrEmpty(orderId)){
            throw new TBusinessException(1,"orderId can't not be empty");
        }

        Order order = orderDao.selectById(orderId);
        if(Objects.isNull(order)){
            throw new TBusinessException(BusinessExceptionCodeEnum.ORDER_NOT_EXISTS.getCode(),"订单不存在");
        }

        //校验状态
        if(!Order.OrderStatus.confirmed.equals(order.getStatus()) && !Order.OrderStatus.planned.equals(order.getStatus())){
            throw new TBusinessException(BusinessExceptionCodeEnum.ORDER_STATUS_EX.getCode(),"只有状态为【已确认、已派车】才能进行操作");
        }

        //检查操作人是否拥有操作该订单的锁,由于现在锁的机制直接用分单客户的id作为key，因此用操作人id去比较
        checkTheLock(order,operatorId);

        //分配车辆给订单

        if(StringUtils.isNullOrEmpty(dispatchParamDto.getVehicleCode())){
            throw new TBusinessException(1,"vehicleCode can't be null");
        }

        Vehicle vehicle = vehicleAndDriverService.getVehicleByCode(dispatchParamDto.getVehicleCode());
        if(vehicle == null){
            throw new TBusinessException(BusinessExceptionCodeEnum.NOT_EXSITS.getCode(),"自编号为"+dispatchParamDto.getVehicleCode()+"的车辆不存在");
        }
        //检查车辆是否可用
        if("DISABLE".equals(vehicle.getStatus())){
            throw new TBusinessException(1,"当前车辆不可用");
        }

        //若之前存在taskId则将订单从task中移除
        if(Order.OrderStatus.planned.equals(order.getStatus())){
            taskService.removeFromTask(order,order.getTaskId(),operatorId);
        }

        Task task = taskService.assignOrderToVehicle(order, vehicle.getId(),operatorId);

        // 更新订单
        Order updateTemp = new Order();
        updateTemp.setTaskId(task.getId());
        updateTemp.setStatus(Order.OrderStatus.planned);
        updateTemp.setVehicleCode(dispatchParamDto.getVehicleCode());
        //获取操作人信息
        Dispatcher dispatcher = getDispatcher(operatorId);
//        updateTemp.setOperatorId(dispatcher.getId());

        //更新上车地点
        PositionDto aboardPosition = dispatchParamDto.getAboardPosition();
        if(Objects.nonNull(aboardPosition)){
            updateTemp.setAboardPlace(aboardPosition.getPositionDesc());
            if(Objects.nonNull(aboardPosition.getArea()) && StringUtils.isNotBlank(aboardPosition.getArea().getId())){
                updateTemp.setAboardLati(aboardPosition.getArea().getId());
            }
        }



        if(dispatchParamDto.getExpectBoardTime() <= 0){
            throw new TBusinessException(1,"expectBoardTime can't be null");
        } else {
            updateTemp.setExpectBoardTime(new Date(dispatchParamDto.getExpectBoardTime()));

        }
        if(dispatchParamDto.getFlightDepTime() > 0){
            updateTemp.setFlightDepTime(new Date(dispatchParamDto.getFlightDepTime()));
        }

        //更新到数据库
        UpdateByIdParam updateByIdParam = new UpdateByIdParam(order.getId(),updateTemp);
        orderDao.updateByIdSelective(updateByIdParam);

        //清除锁
        clearLock(order.getId());


        //  日志记录
        //日志记录
        //产品文案：文案应显示如“将订单指派给司机王自健（910）
        //描述地址：http://redmine.ikamobile.com/issues/21632
        Driver driver = vehicleAndDriverService.getDriverById(task.getDriverId());
        String driverName = "";
        if(Objects.nonNull(driver)){
            driverName = driver.getName();
        }
        //司机code在车上！！！！
        String driverCode = vehicle.getCode();
        String msg = String.format("将订单指派给司机%s（%s）",driverName,driverCode);
        operateLogService.orderBack(orderId,msg,dispatcher.getLoginName());
        return new OperateResponse();

    }

    @Override
    public OperateResponse unDispatch(String orderId, String taskId,String operatorId) throws TBusinessException, TException {

        Order order = orderDao.selectById(orderId);
        if(Objects.isNull(order)){
            throw new TBusinessException(BusinessExceptionCodeEnum.ORDER_NOT_EXISTS.getCode(),"订单不存在");
        }

        //校验状态
        if(!Order.OrderStatus.planned.equals(order.getStatus())&&!Order.OrderStatus.exception.equals(order.getStatus())){
            throw new TBusinessException(BusinessExceptionCodeEnum.ORDER_STATUS_EX.getCode(),"订单状态不正确，只有【已派车,异常订单】状态才能取消");
        }
        //校验锁
        checkTheLock(order,operatorId);

        //校验taskId。用来做简单的幂等性
        if(StringUtils.isNullOrEmpty(order.getTaskId()) || !order.getTaskId().equals(taskId)){
            throw new TBusinessException(BusinessExceptionCodeEnum.ORDER_DATA_CHANGED.getCode(),"订单数据已修改");
        }


        taskService.removeFromTask(order,taskId,operatorId);

        //更新

        order.setVehicleCode(null);
        order.setTaskId(null);

        //异常订单就不再进行待处理订单了
        if(Order.OrderStatus.planned.equals(order.getStatus())){
            order.setStatus(Order.OrderStatus.confirmed);
        }
        order.setExpectBoardTime(null);
        order.setUpdateTime(new Date());
        //操作人信息
        Dispatcher dispatcher = getDispatcher(operatorId);
//        order.setOperatorId(dispatcher.getId());

        orderDao.updateById(new UpdateByIdParam(order.getId(),order));
        //日志记录
        //产品文案：将订单从司机王自健（910）那移除
        //描述地址：http://redmine.ikamobile.com/issues/21632

        //获取司机code
        //注意：司机code放在vehicle表里面。！！！
        Task task = taskDao.selectById(taskId);
        if(Objects.isNull(task)){
            throw new TBusinessException(1,"任务不存在");
        }
        Vehicle vehicle = vehicleAndDriverService.getVehicleById(task.getVehicleId());
        String driverCode = "";
        if(Objects.nonNull(vehicle)){
            driverCode = vehicle.getCode();
        }
        //获取司机名字
        Driver driver = vehicleAndDriverService.getDriverById(task.getDriverId());
        String driverName = "";
        if(Objects.nonNull(driver)){
            driverName = driver.getName();
        }
        task.getVehicleId();
        String msg = String.format("将订单从司机%s（%s）那移除",driverName,driverCode);
        operateLogService.orderBack(orderId,msg,dispatcher.getLoginName());

        clearLock(orderId);//清楚锁
        return new OperateResponse();
    }


    @Override
    @Transactional
    public boolean confirmPassengerAboard(String orderId, String driverId) {
        //
        Order order = orderDao.selectById(orderId);
        if(Objects.isNull(order)){
            throw new BusinessException("订单不存在");
        }

        //状态判断
        if(!Order.OrderStatus.waiting.equals(order.getStatus())){
            throw new BusinessException("订单状态不为【待接】,不能进行该操作");
        }

        Order updateTemp = new Order();
        updateTemp.setStatus(Order.OrderStatus.picked);
        orderDao.updateByIdSelective(new UpdateByIdParam(orderId,updateTemp));
        //  记录日志
        Driver driver = vehicleAndDriverService.getDriverById(driverId);
        if(driver == null){
            throw new BusinessException("司机不存在");
        }
        operateLogService.orderFront(orderId,"司机确定乘客上车",driver.getName());
        return true;
    }

    @Override
    public boolean confirmPassengerMiss(String orderId, String driverId) {
        Order order = orderDao.selectById(orderId);
        if(Objects.isNull(order)){
            throw new BusinessException("订单不存在");
        }
        //状态判断
        if(!Order.OrderStatus.waiting.equals(order.getStatus())){
            throw new BusinessException("订单状态不为【待接】,不能进行该操作");
        }

        Order updateTemp = new Order();
        updateTemp.setStatus(Order.OrderStatus.missed);
        orderDao.updateByIdSelective(new UpdateByIdParam(orderId,updateTemp));
        //  记录日志
        Driver driver = vehicleAndDriverService.getDriverById(driverId);
        operateLogService.orderFront(orderId,"订单异常，司机未接到乘客",driver.getName());
        return true;
    }

    @Override
    public OperateResponse lockOrder(String orderId, String operatorId) throws TBusinessException, TException {
        //锁逻辑
        //1. 查询订单
        try{
            log.info("operator[id={}] lock the order [id={}]",operatorId,orderId);
            OperateResponse response = new OperateResponse();
            Order order = orderDao.selectById(orderId);
            if(order == null){
                throw new TBusinessException(BusinessExceptionCodeEnum.ORDER_NOT_EXISTS.getCode(),"订单不存在");
            }

            checkTheLock(order,operatorId);
            Order updateTemp = new Order();
            updateTemp.setLockKey(operatorId);
            updateTemp.setUpdateTime(new Date());
            orderDao.updateByIdSelective(new UpdateByIdParam(orderId, updateTemp));
            response.setMessage("操作成功");
            response.setOperateCode(OperateCode.success);
            log.info("pa core lock order success,ready to return response");
            return response;
        }catch (Exception e){
            log.error("lock occurred some ex===>",e);
            throw e;
        }
    }


    @Override
    public OperateResponse unlock(String orderId, String operatorId) throws TBusinessException, TException {
        //解锁逻辑
        //1. 查询订单
        OperateResponse response = new OperateResponse();
        Order order = orderDao.selectById(orderId);
        if(order == null){
            throw new TBusinessException(BusinessExceptionCodeEnum.ORDER_NOT_EXISTS.getCode(),"订单不存在");
        }
        //2. 解锁。a.若改订单已经被锁，提供lockKey进行解锁，若没上锁则操作成功
        if(StringUtils.isNullOrEmpty(order.getLockKey())){
            response.setOperateCode(OperateCode.success);
            response.setMessage("操作成功");
            return response;

        }
        checkTheLock(order,operatorId);

        clearLock(order.getId());
        response.setMessage("操作成功");
        response.setOperateCode(OperateCode.success);
        return response;

    }

    private void clearLock(String orderId){
        Order order = orderDao.selectById(orderId);
        if(order == null || StringUtils.isNullOrEmpty(order.getLockKey())){
            return;
        }
        order.setLockKey(null);
        order.setUpdateTime(new Date());
        orderDao.updateById(new UpdateByIdParam(order.getId(), order));
    }
}
