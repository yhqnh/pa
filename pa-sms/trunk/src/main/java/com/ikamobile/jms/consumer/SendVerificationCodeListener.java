package com.ikamobile.jms.consumer;

import com.ikamobile.service.PaSmsService;
import com.ikamobile.pa.message.common.utils.MessageUtils;
import com.ikamobile.pa.message.service.jms.entity.VerificationCodeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 * Created by Administrator on 2016/7/12.
 */
@Component
@Slf4j
public class SendVerificationCodeListener implements MessageListener{

    @Autowired
    private PaSmsService paSmsService;

    @Override
    public void onMessage(Message message) {
        try {
            String msgStr = ((TextMessage)message).getText();
            log.info("receive msg id {} context: {}",message.getJMSMessageID(),msgStr);
            final VerificationCodeMessage verificationCodeMessage  =  MessageUtils.parseMessageString(msgStr,VerificationCodeMessage.class);
            paSmsService.sendVerifyCode(verificationCodeMessage);
            message.acknowledge();//手动做消息确认操作
        } catch (Exception e) {
            try {
                log.warn("something wrong! message content: {}",((TextMessage)message).getText());
            } catch (JMSException e1) {
                log.error("fetal error:",e);
            }
            throw new RuntimeException(e);
        }

    }
}