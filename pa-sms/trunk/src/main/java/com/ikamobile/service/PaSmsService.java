package com.ikamobile.service;

import com.ikamobile.service.exception.SMSException;
import com.ikamobile.pa.message.service.jms.entity.AccountMessage;
import com.ikamobile.pa.message.service.jms.entity.OrderMessage;
import com.ikamobile.pa.message.service.jms.entity.TaskMessage;
import com.ikamobile.pa.message.service.jms.entity.VerificationCodeMessage;

import javax.jms.JMSException;

/**
 * Created by Administrator on 2016/7/10.
 * 短信发送主题内容
 */
public interface PaSmsService {
    /**
     * 当用户登录时发送短信获取验证码主题消息，短信模块需要短信通知用户处理
     * @param verificationCodeMessage
     * @throws SMSException
     * @throws JMSException
     */
    void sendVerifyCode(VerificationCodeMessage verificationCodeMessage) throws SMSException, JMSException;

    /**
     * 任务开始后给司机发短信
     * @param orderMessage
     * @throws SMSException
     * @throws JMSException
     */
    void orderStartNotify(OrderMessage orderMessage) throws SMSException, JMSException;

    /**
     * 任务开始后给司机发送短信
     * @param taskMessage
     * @throws SMSException
     * @throws JMSException
     */
    void taskStartNotify(TaskMessage taskMessage) throws SMSException, JMSException;

    /**
     *新增车辆时，自动生成账号密码，并以短信发送到司机手机主题，通知司机处理
     * @param accountMessage
     * @throws SMSException
     * @throws JMSException
     */
    void sendAccount(AccountMessage accountMessage) throws SMSException, JMSException;

}
