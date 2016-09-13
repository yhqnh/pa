package com.ikamobile.pa.clientsapi.controller;

import com.alibaba.fastjson.JSON;
import com.ikamobile.pa.clientsapi.common.util.StringUtils;
import com.ikamobile.pa.clientsapi.controller.handler.OrderDetailHandler;
import com.ikamobile.pa.clientsapi.controller.param.FinishParam;
import com.ikamobile.pa.clientsapi.controller.result.OrderDetail;
import com.ikamobile.pa.clientsapi.controller.result.TaskDetail;
import com.ikamobile.pa.clientsapi.response.BaseResponse;
import com.ikamobile.pa.clientsapi.response.SimpleResponse;
import com.ikamobile.pa.thrift.client.TaskServiceClientProxy;
import com.ikamobile.pa.thrift.common.OperateResponse;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.TaskDetailDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zhangcheng on 2016/7/7.
 */
@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {
    @Resource
    private TaskServiceClientProxy taskServiceClientProxy;



    /**
     * 获取司机当前进行的任务详情
     * @param driverId
     * @return 任务详情对象，如果没有任务返回空
     */
    @RequestMapping(value = "/{driverId}",method = RequestMethod.GET)
    public SimpleResponse<TaskDetail> getCurrentTask(@PathVariable String driverId) throws TException {
        SimpleResponse simpleResponse = new SimpleResponse();
        TaskDetailDto taskDetailDto = taskServiceClientProxy.createProxy().getDriverCurrentTask(driverId);
        log.debug("获取任务详情：{}", JSON.toJSON(taskDetailDto));
        if(taskDetailDto!=null) {
            TaskDetail taskDetail = new TaskDetail();
            Transform.transform(taskDetailDto,taskDetail);
            for(OrderDetail orderDetail : taskDetail.getOrders()){
                orderDetail.setStatusName(OrderDetailHandler.orderStatusMap.get(orderDetail.getStatus()));
            }
            log.debug("getCurrentTask 返回 {} ",JSON.toJSONString(taskDetail));
            simpleResponse.setData(taskDetail);
        }else{
            simpleResponse.setMessage("无数据");
        }
        return simpleResponse;
    }


    /**
     * 司机端确认乘客上车
     *
     * @param orderId 订单id
     * @return SimpleResponse
     */
    @RequestMapping(value = "/{orderId}/aboard", method = RequestMethod.POST)
    public BaseResponse aboardAction(@NotEmpty @PathVariable String orderId,@NotEmpty String driverId) {
        BaseResponse response = new BaseResponse();
        try {
            OperateResponse operateResponse = taskServiceClientProxy.createProxy().confirmPassengerAboard(orderId, driverId);
            response.setCode(operateResponse.getOperateCode().getValue());
            response.setMessage(operateResponse.getMessage());
        } catch (TBusinessException e) {
            log.warn("driver confirm passenger aboard occurred some ex",e);
            response.setCode(1);
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage():"系统异常");
        } catch (Exception e){
            log.error("driver confirm passenger aboard occurred some sys ex", e);
            response.setCode(1);
            response.setMessage("系统异常");
        }

        return response;
    }


    /**
     * 司机确认没接到乘客
     * @param orderId 订单id
     * @return SimpleResponse
     */
    @RequestMapping(value = "/{orderId}/miss",method = RequestMethod.POST)
    public BaseResponse passengerMissAction(@NotEmpty @PathVariable String orderId,@NotEmpty String driverId){
        BaseResponse response = new BaseResponse();
        try {
            OperateResponse operateResponse = taskServiceClientProxy.createProxy().confirmPassengerMiss(orderId, driverId);
            response.setCode(operateResponse.getOperateCode().getValue());
            response.setMessage(operateResponse.getMessage());
        } catch (TBusinessException e) {
            log.warn("driver confirm passenger miss occurred some ex",e);
            response.setCode(1);
            response.setMessage(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage():"系统异常");
        } catch (Exception e){
            log.error("driver confirm passenger miss occurred some sys ex", e);
            response.setCode(1);
            response.setMessage("系统异常");
        }

        return response;
    }


    /**
     * 获取任务详情
     * @param taskId 任务ID
     * @return 任务详情对象
     * @throws TException
     */
//    @RequestMapping(value = "/detail/{taskId}",method = RequestMethod.GET)
//    public SimpleResponse<TaskDetail> getDetail(@PathVariable String taskId)throws TException{
//        return null;
//    }



    /**
     * 完成任务（全部送达）
     * @param finishParam 完成订单参数
     * @return 操作结果
     */
    @RequestMapping(value = "/finish",method = RequestMethod.POST)
    public SimpleResponse finish(FinishParam finishParam) throws TException {
        OperateResponse response = taskServiceClientProxy.createProxy().finishTask(finishParam.getTaskId(), finishParam.getDriverId());
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setCode(response.getOperateCode().getValue());
        simpleResponse.setMessage(response.getMessage());
        return simpleResponse;
    }


    public String getDriverId() {
        // todo 改成从shiro权限上下午中获取
        return "042614dd-0751-43c0-9e40-bd7281076a64";//18980096952,陈  建
    }
}
