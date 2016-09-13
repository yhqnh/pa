package com.ikamobile.pa.service;

import com.ikamobile.pa.dao.model.Dispatcher;
import com.ikamobile.pa.dao.model.Driver;
import com.ikamobile.pa.dao.model.Order;
import com.ikamobile.pa.dao.model.Task;
import com.ikamobile.pa.thrift.common.TBusinessException;
import com.ikamobile.pa.thrift.server.acceptor.OrderDetailDto;
import com.ikamobile.pa.thrift.server.acceptor.ThriftOrderService;

import java.util.List;

/**
 * Created by zhangcheng on 2016/7/13.
 */
public interface OrderService extends ThriftOrderService.Iface {

    /**
     * 获取任务下的所有订单
     * @param taskId
     * @return
     */
    List<Order> getByTaskId(String taskId);

    /**
     * 状态修改为已确认状态（需要保存）
     * @param order
     */
    void changeToConfirmed(Order order);


    /**
     * 更新任务信息（车辆、司机、任务、状态等信息）
     * 需要保存
     * @param order
     * @param task
     */
    void updateToTask(Order order, Task task);

    /**
     * 任务开始，将所有订单设置为待接,发送短信
     * @param task
     */
    void changeAllToWaiting(Task task,Dispatcher dispatcher) throws TBusinessException;

    /**
     * 任务开始，将所有订单设置为已送达（如果已经设置为异常则跳过）
     * @param task
     */
    void changeAllToServed(Task task, Driver driver);

    Order getOrderById(String uuid);

    boolean confirmPassengerAboard(String orderId,String driverId);

    boolean confirmPassengerMiss(String orderId,String driverId);

    OrderDetailDto buildOrderDetail(Order order);
}
