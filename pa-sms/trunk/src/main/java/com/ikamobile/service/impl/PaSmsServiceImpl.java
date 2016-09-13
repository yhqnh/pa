package com.ikamobile.service.impl;

import com.ikamobile.dao.SMSRecordService;
import com.ikamobile.service.PaSmsService;
import com.ikamobile.service.SMSVendor;
import com.ikamobile.service.exception.SMSException;
import com.ikamobile.pa.message.service.jms.entity.AccountMessage;
import com.ikamobile.pa.message.service.jms.entity.OrderMessage;
import com.ikamobile.pa.message.service.jms.entity.TaskMessage;
import com.ikamobile.pa.message.service.jms.entity.VerificationCodeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/11.
 */
@Service
@Slf4j
public class PaSmsServiceImpl implements PaSmsService {
    @Autowired
    private  SMSVendor smsVendor;
    @Autowired
    private SMSRecordService smsRecordService;

    @Value("${jms.pa.sms.validation.code.template}")
    private String validationCodeTemplate;
    @Value("${jms.pa.sms.order.start.template}")
    private String confirmOrderViewTemplate;
    @Value("${jms.pa.sms.account.template}")
    private String sendAccountPasswordTemplate;

    @Override
    public void sendVerifyCode(VerificationCodeMessage verificationCodeMessage) throws SMSException, JMSException {
        processForPassenger(verificationCodeMessage);

    }

    @Override
    public void orderStartNotify(OrderMessage orderMessage) throws SMSException, JMSException {
        processForConfirm(orderMessage);

    }

    @Override
    public void taskStartNotify(TaskMessage taskMessage) throws SMSException, JMSException {
        processForTaskStart(taskMessage);
    }

    @Override
    public void sendAccount(AccountMessage accountMessage) throws SMSException, JMSException {
        processForAccount(accountMessage);
    }



    private void processForPassenger(VerificationCodeMessage verificationCodeMessage) throws SMSException {
        String mobile = verificationCodeMessage.getMobile();
        String verificationCode= verificationCodeMessage.getVerificationCode();
        String message = MessageFormat.format(validationCodeTemplate,verificationCode);
        sendMessage(mobile,message);
    }

    private void processForConfirm(OrderMessage orderMessage) throws SMSException {
        String mobile = orderMessage.getMobile();

        String arrivePlace=orderMessage.getArrivePlace();
        String driveName = orderMessage.getDriverName();
        String driverMobile = orderMessage.getDriverMobile();
        Date arriveTime = orderMessage.getArriveTime();

        SimpleDateFormat formatter = new SimpleDateFormat();
        formatter.applyPattern("M月d日H:mm");
        String time=formatter.format(arriveTime);

        String message = MessageFormat.format(confirmOrderViewTemplate,time,arrivePlace,driveName,driverMobile);

        sendMessage(mobile, message);

    }

    private void processForTaskStart(TaskMessage taskMessage) throws SMSException {
        String mobile = taskMessage.getMobile();
        String message = "您有新任务来了【四川航空】";
        sendMessage(mobile, message);

    }



    private void processForAccount(AccountMessage accountMessage) throws SMSException {
        String mobile = accountMessage.getMobile();

        String account= accountMessage.getAccount();
        String password = accountMessage.getPassword();

        String message = MessageFormat.format(sendAccountPasswordTemplate,account,password);

        sendMessage(mobile, message);
    }


    private void sendMessage(String mobile, String message) {
        try {
            //调用第三方接口发送短信
            smsVendor.send(mobile,message);
            //保存数据
            smsRecordService.save("notify",message,mobile);

        } catch (Exception e) {
            log.error("发送短信失败:{}-{}",mobile,message,e);
        }
    }

}
