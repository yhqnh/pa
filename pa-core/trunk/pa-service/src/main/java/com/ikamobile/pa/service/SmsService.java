package com.ikamobile.pa.service;

import com.ikamobile.pa.dao.model.Driver;
import com.ikamobile.pa.dao.model.Order;
import com.ikamobile.pa.dao.model.Task;

/**
 * Created by zhangcheng on 2016/7/15.
 */
public interface SmsService {

    /**
     * 发送短信验证码
     * @param mobile
     * @param verifyCode
     * @param tag
     */
    public void sendVerifyCode(String mobile,String verifyCode,String tag);

    /**
     * 发车后发送短信提醒预定人
     * @param mobile
     * @param order
     * @param driver
     */
    public void sendNotifyWhenOrderStart(String mobile, Order order, Driver driver);

    /**
     * 任务开始发送短信给司机
     * @param mobile
     * @param task
     */
    public void sendNotifyWhenTaskStart(String mobile, Task task);


}
