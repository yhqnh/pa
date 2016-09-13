package com.ikamobile.pa.dispatchapi.controller;

import com.alibaba.fastjson.JSON;
import com.ikamobile.pa.dispatchapi.controller.param.*;
import com.ikamobile.pa.dispatchapi.controller.param.OrderCreateParam;
import com.ikamobile.pa.dispatchapi.controller.param.sorter.OrderQuerySorterParam;
import com.ikamobile.pa.dispatchapi.controller.param.sorter.SorterEnum;
import com.ikamobile.pa.dispatchapi.controller.result.OrderDetail;
import com.ikamobile.pa.dispatchapi.response.BaseResponse;
import com.ikamobile.pa.dispatchapi.response.PageContent;
import com.ikamobile.pa.dispatchapi.response.SimpleResponse;
import com.ikamobile.pa.dispatchapi.util.StringUtils;
import com.ikamobile.pa.thrift.client.OrderServiceClientProxy;
import com.ikamobile.pa.thrift.common.OperateResponse;
import com.ikamobile.pa.thrift.common.PagerDto;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.thrift.TException;
import org.dozer.DozerBeanMapper;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 订单接口
 * Created by yanghuqianghq on 2016/7/01.
 */
@RestController
@RequestMapping("/pa/dispatcher/order")
@Slf4j
@Validated
public class OrderController {

    @Resource
    OrderServiceClientProxy orderServiceClientProxy;

    @Resource
    DozerBeanMapper dozerBeanMapper;


    @RequestMapping(value = "",method = RequestMethod.POST)
    public SimpleResponse<OrderDetail> createAction(@Validated OrderCreateParam orderCreateParam){

        log.debug("create order with param===>{}",JSON.toJSONString(orderCreateParam));
        SimpleResponse<OrderDetail> response = new SimpleResponse<>();

        try{
            com.ikamobile.pa.thrift.server.acceptor.OrderCreateParam param = new com.ikamobile.pa.thrift.server.acceptor.OrderCreateParam();
            dozerBeanMapper.map(orderCreateParam,param);
            param.setOperatorId(getOperatorId());
            param.setIsOnline(false);
            OrderDetailDto orderDetailDto = orderServiceClientProxy.createProxy().create(param);
            response.setData(convertToOrderDetail(orderDetailDto));
        } catch (TBusinessException e) {

            log.warn("dispatch sys createAction order occurred some ex,with the param==>{}", JSON.toJSONString(orderCreateParam), e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (TException e){
            //系统异常
            log.warn("dispatch sys createAction order occurred some sys ex,with the param==>{}", JSON.toJSONString(orderCreateParam), e);
            response.setCode(500);
            response.setMessage("系统异常");
        }
        return response;
    }


    /**
     * 获取订单详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public SimpleResponse<OrderDetail> detailAction(@PathVariable @NotBlank(message = "orderId不能为空") String id){

        SimpleResponse<OrderDetail> response = new SimpleResponse<>();
        try {
            OrderDetailDto dto = orderServiceClientProxy.createProxy().orderDetail(id);
            if(dto == null){
                throw new TBusinessException(1,"订单不存在");
            }
            OrderDetail orderDetail = convertToOrderDetail(dto);
            response.setData(orderDetail);
            response.setCode(0);
        } catch (TBusinessException e) {
            log.warn("get order detail[id={}] occurred some ex", id, e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (TException e){
            //系统异常
            log.error("get order detail[id={}] occurred some sys ex",id,e);
            response.setCode(500);
            response.setMessage("系统异常");
        }
        return response;
    }


    /**
     * 获取订单列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public SimpleResponse<PageContent<OrderDetail>> listAction(OrderSearchSorterPageParam param){

        SimpleResponse<PageContent<OrderDetail>> response = new SimpleResponse<>();
        com.ikamobile.pa.thrift.server.acceptor.OrderQueryParam orderQueryPram = new com.ikamobile.pa.thrift.server.acceptor.OrderQueryParam();

        //设置分页信息
        PagerDto pagerDto = new PagerDto();
        if(param.getPageSize() >0 && param.getPageIndex() > 0){
            pagerDto.setPageIndex(param.getPageIndex());
            pagerDto.setPageSize(param.getPageSize());
        } else {//使用默认配置
            pagerDto.setPageIndex(PageParam.DEFAULT_PAGE_INDEX);
            pagerDto.setPageSize(PageParam.DEFAULT_PAGE_SIZE);
        }
        orderQueryPram.setPager(pagerDto);

        //设置默认排序信息
        if(param.getSorter() == null){
            OrderQuerySorterParam querySorterParam = new OrderQuerySorterParam();
            querySorterParam.setCreateTime(SorterEnum.DESC);
            param.setSorter(querySorterParam);
        }

        //设置条件信息
        dozerBeanMapper.map(param,orderQueryPram);
        if(param.getSearchParam() != null){
            if(param.getSearchParam().getDriverId() != null ){
                orderQueryPram.getSearchParam().setVehicleCode(param.getSearchParam().getDriverId());
            }
        }

        try {
            OrderPageResponse pageResponse = orderServiceClientProxy.createProxy().listOrder(orderQueryPram);

            PageContent<OrderDetail> data = new PageContent<>();
            List<OrderDetail> orderDetailList = new ArrayList<>();
            for(OrderDetailDto detailDto : pageResponse.getPageContent()){
                OrderDetail orderDetail = convertToOrderDetail(detailDto);
                orderDetailList.add(orderDetail);
            }
            data.setPageContent(orderDetailList);

            //设置分页信息
            data.setPageSize(pageResponse.getPagerInfo().getPageSize());
            data.setPageIndex(pageResponse.getPagerInfo().getPageIndex());
            data.setTotalPageNum(pageResponse.getPagerInfo().getTotalPageNum());
            data.setTotalRowNum(pageResponse.getPagerInfo().getTotalRowNum());

            response.setData(data);
            response.setCode(0);
        } catch (TBusinessException e) {
            log.warn("get order list occurred some ex", e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (TException e) {
            //系统异常
            log.error("get order list occurred some sys ex",e);
            response.setCode(500);
            response.setMessage("系统异常");
        }
        return response;
    }


    /**
     *
     * 待处理订单列表
     * @param orderSearchSorterPageParam
     */
    @RequestMapping(value = "/pending",method = RequestMethod.GET)
    public SimpleResponse<PageContent<OrderDetail>> pendingAction(OrderSearchSorterPageParam orderSearchSorterPageParam){
//        OrderSearchSorterPageParam orderSearchSorterPageParam = new OrderSearchSorterPageParam();
//        orderSearchSorterPageParam.setPageIndex(pageParam.getPageIndex());
//        orderSearchSorterPageParam.setPageSize(pageParam.getPageSize());
//
//        orderSearchSorterPageParam.setSearchParam(new OrderQueryPram());
        if(orderSearchSorterPageParam == null){
            orderSearchSorterPageParam = new OrderSearchSorterPageParam();
        }
        if(orderSearchSorterPageParam.getSearchParam() == null){
            orderSearchSorterPageParam.setSearchParam(new OrderQueryPram());
        }
        List<String> filterStatus = new ArrayList<>();
        //根据业务调整，目前待处理订单，只包含，未确认订单。
        filterStatus.add("unconfirmed");
        filterStatus.add("confirmed");
        orderSearchSorterPageParam.getSearchParam().setStatus(filterStatus);
        return this.listAction(orderSearchSorterPageParam);
    }




    /**
     *
     * 通过验证，开始派车
     * @param orderId 订单id
     */
    @RequestMapping(value = "/accept",method = RequestMethod.POST)
    public BaseResponse acceptOrderAction( @NotEmpty(message = "订单号不能为空") String orderId){
        BaseResponse response = new BaseResponse();
        try {
            OperateResponse accept = orderServiceClientProxy.createProxy().accept(orderId, getOperatorId());
            response.setCode(0);
            response.setMessage(accept.getMessage());
        } catch (TBusinessException e) {
            log.warn("accept order  occurred some ex", e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (TException e) {
            //系统异常
            log.error("accept order  occurred some sys ex",e);
            response.setCode(500);
            response.setMessage("系统异常");
        }
        return response;
    }

    /**
     *
     * 分配车辆给订单
     * @param dispatchParam 分配订单到
     */
    @RequestMapping(value = "/dispatch",method = RequestMethod.POST)
    public BaseResponse dispatchAction(@Validated DispatchParam dispatchParam){
        BaseResponse response = new BaseResponse();
        try {
            DispatchParamDto paramDto = new DispatchParamDto();
            dozerBeanMapper.map(dispatchParam,paramDto);
            OperateResponse dispatch = orderServiceClientProxy.createProxy().dispatch(paramDto, getOperatorId());
            response.setCode(0);
            response.setMessage(dispatch.getMessage());

        } catch (TBusinessException e) {
            log.warn("dispatch order  occurred some ex",e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (TException e){
            //系统异常
            log.error("dispatch order  occurred some sys ex",e);
            response.setCode(500);
            response.setMessage("系统异常");
        }
        return response;
    }

    /**
     * 拒绝订单
     * @param orderId 订单id
     */
    @RequestMapping(value = "/deny",method = RequestMethod.POST)
    public BaseResponse denyOrderAction(@NotBlank(message = "订单id不能为空") String orderId){
        BaseResponse response = new BaseResponse();
        try {
            OperateResponse deny = orderServiceClientProxy.createProxy().deny(orderId, getOperatorId());
            response.setCode(0);
            response.setMessage(deny.getMessage());
        } catch (TBusinessException e) {
            log.warn("deny order  occurred some ex",e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (TException e){
            log.error("deny order  occurred some sys ex",e);
            //系统异常
            response.setCode(500);
            response.setMessage("系统异常");
        }
        return response;
    }




    /**
     * 取消订单
     * @param orderId 订单id
     */
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    public BaseResponse cancelOrderAction(@NotBlank String orderId){
        BaseResponse response = new BaseResponse();
        try {
            OperateResponse deny = orderServiceClientProxy.createProxy().dispactherCancelOrder(orderId, getOperatorId());
            response.setCode(0);
            response.setMessage(deny.getMessage());
        } catch (TBusinessException e) {
            log.warn("cancel order  occurred some ex",e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (TException e){
            log.error("cancel order  occurred some sys ex",e);
            //系统异常
            response.setCode(500);
            response.setMessage("系统异常");
        }
        return response;
    }
    /**
     * 订单取消分配
     * @param orderId 订单ID
     * @param taskId  任务ID 为了防止多人操作时，后操作者覆盖其他人的分配结果
     * @return
     */
    @RequestMapping(value = "/unDispatch",method = RequestMethod.POST)
    public BaseResponse unDispatchAction(@NotBlank String orderId ,@NotBlank String taskId){
        BaseResponse response = new BaseResponse();
        try {
            OperateResponse tRes = orderServiceClientProxy.createProxy().unDispatch(orderId, taskId, getOperatorId());
            response.setCode(0);
            response.setMessage(tRes.getMessage());
        } catch (TBusinessException e) {
            log.warn("unDispatch order  occurred some ex",e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (TException e){
            log.error("unDispatch order  occurred some sys ex",e);
            //系统异常
            response.setCode(500);
            response.setMessage("系统异常");
        }
        return response;
    }


    /**
     * 订单上锁
     * @param orderId 订单ID
     * @return
     */
    @RequestMapping(value = "/lock",method = RequestMethod.POST)
    public BaseResponse lockAction(@NotBlank String orderId){
        BaseResponse response = new BaseResponse();
        try {
            ThriftOrderService.Client proxy = orderServiceClientProxy.createProxy();
//            Thread.sleep(3000);
            OperateResponse tRes = proxy.lockOrder(orderId, getOperatorId());
            response.setCode(0);
            response.setMessage(tRes.getMessage());
        } catch (TBusinessException e) {
            log.warn("lock order  occurred some ex",e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (Exception e){
            log.error("lock order  occurred some sys ex",e);
            //系统异常
            response.setCode(500);
            response.setMessage("系统异常");
        }
        return response;
    }

    /**
     * 订单上锁
     * @param orderId 订单ID
     * @return
     */
    @RequestMapping(value = "/unlock",method = RequestMethod.POST)
    public BaseResponse unlockAction(@NotBlank String orderId){
        BaseResponse response = new BaseResponse();
        try {
            OperateResponse tRes = orderServiceClientProxy.createProxy().unlock(orderId, getOperatorId());
            response.setCode(0);
            response.setMessage(tRes.getMessage());
        } catch (TBusinessException e) {
            log.warn("unlock order  occurred some ex",e);
            response.setCode(e.getCode());
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "未知错误");
            //业务异常
        } catch (TException e){
            log.error("unlock order  occurred some sys ex",e);
            //系统异常
            response.setCode(500);
            response.setMessage("系统异常");
        }
        return response;
    }





    private OrderDetail convertToOrderDetail(OrderDetailDto orderDetailDto){
        OrderDetail map = dozerBeanMapper.map(orderDetailDto, OrderDetail.class);
        return map;
    }


    private String getOperatorId() throws TBusinessException {
        Subject currentUser = SecurityUtils.getSubject();
        if(Objects.isNull(currentUser)){
            throw new TBusinessException(602,"请求先登录");
        }
        try{
            PaUserDto paUserDto = (PaUserDto) currentUser.getPrincipal();
            return paUserDto.getId();
        }catch (Exception e){
            log.error("getOperatorId occurred some ex",e);
        }

        throw new TBusinessException(602,"请求先登录");
    }

}
