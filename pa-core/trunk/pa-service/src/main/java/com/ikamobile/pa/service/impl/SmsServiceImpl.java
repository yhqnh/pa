package com.ikamobile.pa.service.impl;

import com.ikamobile.pa.common.SystemInfo;
import com.ikamobile.pa.common.exception.BusinessException;
import com.ikamobile.pa.dao.model.Driver;
import com.ikamobile.pa.dao.model.Order;
import com.ikamobile.pa.dao.model.Task;
import com.ikamobile.pa.message.service.jms.entity.OrderMessage;
import com.ikamobile.pa.message.service.jms.entity.TaskMessage;
import com.ikamobile.pa.message.service.jms.entity.VerificationCodeMessage;
import com.ikamobile.pa.service.SmsService;
import com.ikamobile.pa.service.jms.SimpleProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangcheng on 2016/7/15.
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    @Autowired
    private SimpleProducer<VerificationCodeMessage> verificationCodeProducer;

    @Autowired
    private SimpleProducer<OrderMessage> startOrderProducer;

    @Autowired
    private SimpleProducer<TaskMessage> startTaskProducer;
    /**
     * 发送短信验证码
     *
     * @param mobile
     * @param verifyCode
     * @param tag
     */
    @Override
    public void sendVerifyCode(String mobile, String verifyCode, String tag) {
        if(!com.ikamobile.pa.common.utils.StringUtils.isMobile(mobile)){
            throw new BusinessException("手机号码不正确");
        }
        VerificationCodeMessage message = new VerificationCodeMessage();
        message.setMobile(mobile);
        message.setVerificationCode(verifyCode);
        log.info("发送验证码");
        verificationCodeProducer.send(message);
    }

    public void sendNotifyWhenOrderStart(String mobile, Order order, Driver driver){
        OrderMessage message = new OrderMessage();
        message.setMobile(mobile);
        message.setArrivePlace(order.getAboardPlace());
        message.setArriveTime(order.getExpectBoardTime());
        message.setDriverName(driver.getName());
        message.setDriverMobile(driver.getPhoneNumber());
        log.info(">>>>>>>>>>给乘客发短信提醒已发车");
        startOrderProducer.send(message);
    }

    public void sendNotifyWhenTaskStart(String mobile, Task task){
        TaskMessage message = new TaskMessage();
        message.setMobile(mobile);
        message.setCode(task.getCode());
        log.info(">>>>>>>>>>给司机发短信提醒已发车");
        startTaskProducer.send(message);
    }





}
