package com.ikamobile.pa.service.impl;


import com.ikamobile.pa.common.enums.OperateLogTargetType;
import com.ikamobile.pa.common.exception.BusinessException;
import com.ikamobile.pa.common.utils.StringUtils;
import com.ikamobile.pa.dao.DispatcherDao;
import com.ikamobile.pa.dao.TaskDao;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangcheng on 2016/7/12.
 */
@Slf4j
@Service
@Transactional(rollbackFor = {RuntimeException.class, TBusinessException.class})
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private VehicleAndDriverService vehicleAndDriverService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private DispatcherDao dispatcherDao;
    @Autowired
    private OperateLogService operateLogService;

    @Autowired
    private SmsService smsService;

    /**
     * 获取车辆的未开始的任务，如果没有则新建
     * 车辆正在进行任务时返回报错
     * @param vehicleId
     * @return
     */
     private synchronized Task getOrCreateByVehicle(String vehicleId) throws TBusinessException {
        Vehicle vehicle = vehicleAndDriverService.getVehicleById(vehicleId);
        if(vehicle ==null){
            throw new TBusinessException(321,"车辆不存在");
        }
        Driver driver = vehicleAndDriverService.getDriverById(vehicle.getDriverId());


        try {
            TaskDetailDto driverCurrentTask = getDriverCurrentTask(vehicle.getDriverId());
            if (driverCurrentTask!=null){
                throw new TBusinessException(322,"车辆正在执行任务,无法重新分配");
            }
        } catch (TException e) {
            e.printStackTrace();
        }

        CriteriaQuery query = new CriteriaQuery();
        Criteria or = query.or();
        or.andEqualTo(Task.FIELD_VEHICLE_ID,vehicleId);
        or.andEqualTo(Task.FIELD_STATUS, Task.TaskStatus.ready.getValue());
        List<Task> tasks1 = taskDao.selectByCriteriaQuery(query);
        if(tasks1.size() >= 1){
            Task task = tasks1.get(0);
            log.info("获取车辆{}任务{}-{}",vehicle.getCode(), task.getId(), task.getCode());
            return task;
        }
        Task task = new Task();
        task.setCode(vehicle.getCode());//任务code为车辆自编号
        task.setStatus(Task.TaskStatus.ready.getValue());
        task.setDriverId(driver.getId());
        task.setDriverName(driver.getName());
        task.setVehicleId(vehicleId);
        task.setVehicleNum(vehicle.getNumber());
        task.setPsgCount(0);
        task.setOrderCount(0);
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());
        taskDao.insert(task);
        log.info("为车辆{}创建一条任务 {}",vehicle.getCode(),task.getId());
        return task;
    }

    /**
     * 指定订单到车辆对应的任务
     * 如果车辆正在进行任务则报错
     *
     * @param order
     * @param vehicleId
     * @return
     */
    @Override
    public Task assignOrderToVehicle(Order order, String vehicleId,String operatorId) throws TBusinessException {
        log.info("指定订单到任务：订单-{} 车辆-{}",order.getId(),vehicleId);
        Task task = getOrCreateByVehicle(vehicleId);
        task = taskDao.selectByIdForUpdate(task.getId());
        log.info("锁定任务{}-{}",task.getId(),task.getCode());
        task.setPsgCount(task.getPsgCount()+order.getPassengerNum());
        task.setOrderCount(task.getOrderCount()+1);
        if(task.getEarlistFlightTime()==null) {
            task.setEarlistFlightTime(order.getFlightDepTime());
        }else{
            if(task.getEarlistFlightTime().after(order.getFlightDepTime())){
                task.setEarlistFlightTime(order.getFlightDepTime());
            }
        }
        orderService.updateToTask(order,task);

        this.updateTask(task);

        Dispatcher dispatcher = dispatcherDao.selectById(operatorId);
        //记录操作日志
        logOperate(task,order,OperateType.ADD_ORDER,dispatcher.getLoginName());

        return task;
    }

    /**
     * 从任务中删除订单
     *
     * @param order
     * @param taskId
     */
    @Override
    public void removeFromTask(Order order, String taskId,String operatorId) throws TBusinessException {
        log.info("从任务中移除订单：任务-{} 订单-{}",order.getId(),taskId);
        Task task = taskDao.selectByIdForUpdate(taskId);
        if(task==null){
            log.info("任务不存在");
            return;
        }
        log.info("锁定任务{}->{}",task.getId(),task.getCode());
        if(task.getStatus() == Task.TaskStatus.finish.getValue()){
            log.info("任务已经结束");
            throw new TBusinessException(335,"任务已经结束");
        }

//        orderService.changeToConfirmed(order); //订单更新交个调用者类做，这里只负责task相关信息的修改

        List<Order> orders = orderService.getByTaskId(taskId);

        task.setEarlistFlightTime(getEarliestFlightTime(orders));

        task.setPsgCount(task.getPsgCount() - order.getPassengerNum());
        task.setOrderCount(task.getOrderCount() - 1);

        updateTask(task);

        Dispatcher dispatcher = dispatcherDao.selectById(operatorId);
        //记录操作日志
        logOperate(task,order,OperateType.REMOVE_ORDER,dispatcher.getLoginName());
    }




    @Override
    public TaskDetailDto getTaskDetail(String taskID) throws TException {

        Task task = taskDao.selectById(taskID);
        if(task == null){
            throw new TBusinessException();
        }
        TaskDetailDto taskDto = getTaskDetailDto(task);

        return taskDto;
    }





    @Override
    public TaskDetailDto getDriverCurrentTask(String driverId) throws TException {

        CriteriaQuery query = new CriteriaQuery();
        Criteria or = query.or();
        or.andEqualTo(Task.FIELD_STATUS,Task.TaskStatus.carry.getValue());
        or.andEqualTo(Task.FIELD_DRIVER_ID, driverId);
        List<Task> tasks = taskDao.selectByCriteriaQuery(query);
        if(tasks.size()>0){
            return getTaskDetailDto(tasks.get(0));
        }
        return null;
    }

    @Override
    public TaskListResponse getPendingList(PagerDto pageParam,String operatorId) throws TException {

        CriteriaQuery query = new CriteriaQuery();
        Criteria or = query.or();
        //or.andEqualTo(Task.FIELD_STATUS,Task.TaskStatus.ready.getValue());
        or.andGreaterThan(Task.FIELD_PSG_COUNT,0);
        ArrayList<CriteriaQuery.Sort> sorts = new ArrayList<>();
        sorts.add(new CriteriaQuery.Sort(Task.FIELD_STATUS, CriteriaQuery.Sort.Direction.ASC));
        sorts.add(new CriteriaQuery.Sort(Task.FIELD_START_TIME, CriteriaQuery.Sort.Direction.DESC));
        sorts.add(new CriteriaQuery.Sort(Task.FIELD_EARLIST_FLIGHT_TIME, CriteriaQuery.Sort.Direction.DESC));
        query.setSorts(sorts);
        Long total = taskDao.countByCriteriaQuery(query);
        query.setPager(new Pager(pageParam.getPageIndex(),pageParam.getPageSize()));
        List<Task> tasks = taskDao.selectByCriteriaQuery(query);
        List<TaskListDto> result = getTaskListDtos(tasks);
        TaskListResponse response = new TaskListResponse();
        response.setData(result);
        response.setPager(calcPager(pageParam,total));
        return response;
    }



    @Override
    public int getPendingCount() throws TException {
        CriteriaQuery query = new CriteriaQuery();
        Criteria or = query.or();
        or.andEqualTo(Task.FIELD_STATUS,Task.TaskStatus.ready.getValue());
        or.andGreaterThan(Task.FIELD_PSG_COUNT,0);
        return taskDao.countByCriteriaQuery(query).intValue();
    }

    @Override
    public OperateResponse startTask(String taskId,String operatorId,long updateTime) throws TException {
        log.info("操作员{}启动任务{}",operatorId,taskId);
        Task task = taskDao.selectByIdForUpdate(taskId);
        TaskDetailDto currentTask = getDriverCurrentTask(task.getDriverId());
        if(currentTask!=null){
            log.info("车辆正在执行其他任务，无法开始新的任务");
            throw new TBusinessException(333,"车辆正在执行其他任务，无法开始新的任务");
        }
        Vehicle vehicle = vehicleAndDriverService.getVehicleById(task.getVehicleId());
        if(VehicleStatus.DISABLE.name().equals(vehicle.getStatus())){
            log.info("车辆限行，无法开始新的任务");
            throw new TBusinessException(334,"车辆限行，无法开始新的任务");
        }
        if(task.getStatus() == Task.TaskStatus.ready.getValue()){
            if(task.getUpdateTime()!=null&&updateTime != task.getUpdateTime().getTime()){
                log.info("更新时间不符，数据已过时");
                throw new TBusinessException(444,"数据已过时，请刷新后重新操作");
            }
            task.setStatus(Task.TaskStatus.carry.getValue());
            task.setStartTime(new Date());
            updateTask(task);

            Dispatcher dispatcher = dispatcherDao.selectById(operatorId);
            orderService.changeAllToWaiting(task,dispatcher);
            //记录操作日志
            logOperate(task,null,OperateType.START,dispatcher.getLoginName());

            Driver driver = vehicleAndDriverService.getDriverById(task.getDriverId());

            smsService.sendNotifyWhenTaskStart(driver.getPhoneNumber(),task);

        }else{
            log.info("任务状态不是未开始，不能进行该操作");
            throw new TBusinessException(332,"任务状态不是未开始，不能进行该操作");
        }
        return new OperateResponse();
    }

    @Override
    public OperateResponse finishTask(String taskId,String driverID) throws TException {
        log.info("司机{}完成任务{}",driverID,taskId);
        Task task = taskDao.selectById(taskId);
        if(task.getStatus()== Task.TaskStatus.carry.getValue()){
            task.setStatus(Task.TaskStatus.finish.getValue());
            task.setFinishTime(new Date());
            updateTask(task);
            Driver driver = vehicleAndDriverService.getDriverById(driverID);
            orderService.changeAllToServed(task,driver);
            //记录操作日志
            logOperate(task,null,OperateType.FINISH,driver.getName());
        }else{
            log.info("任务状态不是进行中，不能进行该操作");
            throw new TBusinessException(332,"任务状态不是进行中，不能进行该操作");
        }
        return new OperateResponse();
    }


    private void updateTask(Task task) {

        log.info("更新任务数据ID-{}",task.getId());
        task.setUpdateTime(new Date());
        UpdateByIdParam param = new UpdateByIdParam(task.getId(),task);
        taskDao.updateById(param);

    }


    private Date getEarliestFlightTime(List<Order> orders) {
        Date earliest = null;
        for (Order order : orders) {
            if(earliest==null||earliest.after(order.getFlightDepTime())){
                earliest = order.getFlightDepTime();
            }
        }
        return earliest;
    }

    private TaskDetailDto getTaskDetailDto(Task task) {
        TaskDetailDto  taskDto = new TaskDetailDto();
        taskDto.setId(task.getId());
        taskDto.setCode(task.getCode());
        if(task.getCreateTime()!=null) {
            taskDto.setCreateTime(task.getCreateTime().getTime());
        }
        if(task.getEarlistFlightTime()!=null) {
            taskDto.setEarlistFlightTime(task.getEarlistFlightTime().getTime());
        }
        if(task.getFinishTime()!=null) {
            taskDto.setFinishTime(task.getFinishTime().getTime());
        }
        if(task.getStartTime()!=null) {
            taskDto.setStartTime(task.getStartTime().getTime());
        }


        taskDto.setVehicle(vehicleAndDriverService.getVehicleDtoById(task.getVehicleId()));
        taskDto.setDriver(vehicleAndDriverService.getDriverDtoById(task.getDriverId()));
        taskDto.setOrders(new ArrayList<OrderDetailDto>());
        List<Order> orders = orderService.getByTaskId(task.getId());
        int psgNum=0,orderCount=0;
        boolean allException = true;
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            OrderDetailDto detailDto =orderService.buildOrderDetail(order);
            taskDto.getOrders().add(detailDto);
            psgNum+=order.getPassengerNum();
            orderCount+=1;
            if(!Order.OrderStatus.exception.equals(order.getStatus())){
                allException = false;
            }
        }
        //获取详情时核对乘客数量
        allException = allException && orders.size() > 0 && task.getStatus() != Task.TaskStatus.finish.getValue();
        boolean countError = psgNum != task.getPsgCount() || orderCount != task.getOrderCount();
        if(countError||allException){
            task = taskDao.selectByIdForUpdate(task.getId());
            if(countError) {
                log.info("订单乘客数量不符,更新任务数据");
                task.setPsgCount(psgNum);
                task.setOrderCount(orderCount);
            }
            //如果正在进行的任务下面的订单状态全部是异常，则设置为完成
            if(allException){
                log.info("全部为异常订单，更新状态为完成");
                task.setStatus(Task.TaskStatus.finish.getValue());
                operateLogService.saveOperateLog(OperateLogTargetType.TASK,task.getId(),0,"订单全部异常，结束任务","系统");
            }
            updateTask(task);
        }

        taskDto.setStatus(task.getStatus());
        taskDto.setPsgCount(task.getPsgCount());
        if(task.getUpdateTime()!=null) {
            taskDto.setUpdateTime(task.getUpdateTime().getTime());
        }
        return taskDto;
    }

    private PagerInfoDto calcPager(PagerDto pageParam, Long total) {
        PagerInfoDto pager = new PagerInfoDto();
        pager.setPageIndex(pageParam.getPageIndex());
        pager.setPageSize(pageParam.getPageSize());
        pager.setTotalRowNum(total.intValue());
        long l = total / pageParam.getPageSize();
        long mod = total % pageParam.getPageSize();
        if(mod==0) {
            pager.setTotalPageNum(Long.valueOf(l).intValue());
        }else{
            pager.setTotalPageNum(Long.valueOf(l).intValue()+1);
        }
        return pager;
    }


    @Override
    public OperateResponse confirmPassengerAboard(String orderId, String driverId) throws TBusinessException, TException {
        //更改订单状态
        //记录日志
        OperateResponse response = new OperateResponse();
        response.setMessage("操作失败");
        response.setOperateCode(OperateCode.fail);
        try{
            boolean isSuccess = orderService.confirmPassengerAboard(orderId, driverId);
            if(isSuccess){
                response.setOperateCode(OperateCode.success);
                response.setMessage("操作成功");
            }
        }catch (BusinessException e){
            log.warn(" confirmPassengerAboard occurred some ex",e);
            String msg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage():"未知异常";
            throw new TBusinessException(1,msg);
        }


        return response;
    }

    @Override
    public OperateResponse confirmPassengerMiss(String orderId, String driverId) throws TBusinessException, TException {
        //更改订单状态
        //记录日志
        //更改订单状态
        //记录日志
        OperateResponse response = new OperateResponse();
        response.setMessage("操作失败");
        response.setOperateCode(OperateCode.fail);
        try{
            boolean isSuccess = orderService.confirmPassengerMiss(orderId, driverId);
            if(isSuccess){
                response.setOperateCode(OperateCode.success);
                response.setMessage("操作成功");
            }
        }catch (BusinessException e){
            log.warn(" confirmPassengerMiss occurred some ex",e);
            String msg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage():"未知异常";
            throw new TBusinessException(1,msg);
        }

        return response;
    }


    private List<TaskListDto> getTaskListDtos(List<Task> tasks) {
        List<TaskListDto> result = new ArrayList<>();
        if(tasks != null||tasks.size()>0) {
            for (Task task : tasks) {
                TaskListDto dto = new TaskListDto();
                dto.setId(task.getId());
                dto.setStatus(task.getStatus().toString());
                dto.setCode(task.getCode());
                dto.setDriverId(task.getDriverId());
                dto.setDriverName(task.getDriverName());
                Driver driver = vehicleAndDriverService.getDriverById(task.getDriverId());
                dto.setDriverTel(driver.getPhoneNumber());
                dto.setDriverOtherTel(driver.getPhoneNumberOther());
                dto.setEarliestFlightTime(task.getEarlistFlightTime().getTime());
                if(!org.springframework.util.StringUtils.isEmpty(task.getFinishTime())){
                    dto.setFinishTime(task.getFinishTime().getTime());
                }
                if(!org.springframework.util.StringUtils.isEmpty(task.getStartTime())){
                    dto.setStartTime(task.getStartTime().getTime());
                }
                dto.setOrderCount(task.getOrderCount());
                dto.setPassageCount(task.getPsgCount());
                dto.setVehicleId(task.getVehicleId());
                dto.setVehicleNo(task.getVehicleNum());

                result.add(dto);
            }
        }
        return result;
    }


    private void logOperate(Task task, Order order, OperateType type, String operatorName){
        String message = "";
        int level = 0;
        switch (type){
            case ADD_ORDER:
                message="添加订单"+order.getCode();
                break;
            case REMOVE_ORDER:
                message="移除订单"+order.getCode();
                break;
            case START:
                message="任务开始";
                break;
            case FINISH:
                message="结束任务";
                break;
        }

        operateLogService.saveOperateLog(OperateLogTargetType.TASK,task.getId(), level,message,operatorName);
    }

    private enum OperateType {
        ADD_ORDER, REMOVE_ORDER, START, FINISH;
    }
}
