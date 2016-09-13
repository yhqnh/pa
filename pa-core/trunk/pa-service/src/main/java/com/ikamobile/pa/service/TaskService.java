package com.ikamobile.pa.service;

import com.ikamobile.pa.dao.model.Order;
import com.ikamobile.pa.dao.model.Task;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.TaskThriftService;

/**
 * Created by zhangcheng on 2016/7/13.
 */
public interface TaskService extends TaskThriftService.Iface{
    /**
     * 指定订单到车辆对应的任务
     * 如果车辆正在进行任务则报错
     *
     * @param order
     * @param vehicleId
     * @return
     */
    public Task assignOrderToVehicle(Order order, String vehicleId,String operatorId) throws TBusinessException;

    /**
     * 从任务中删除订单
     *
     * @param order
     * @param taskId
     */
    public void removeFromTask(Order order, String taskId,String operatorId) throws TBusinessException;
}
