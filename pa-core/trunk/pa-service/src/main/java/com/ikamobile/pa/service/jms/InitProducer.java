package com.ikamobile.pa.service.jms;

import com.ikamobile.pa.message.service.jms.entity.OrderMessage;
import com.ikamobile.pa.message.service.jms.entity.TaskMessage;
import com.ikamobile.pa.message.service.jms.entity.VerificationCodeMessage;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

/**
 * Created by zhangcheng on 2016/7/15.
 */
@Configuration
public class InitProducer {
    @Value("${sms.verify.code.topic}")
    private String verifyTopic;
    @Value("${sms.start.order.topic}")
    private String startOrderTopic;
    @Value("${sms.start.task.topic}")
    private String startTaskTopic;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Bean
    @Autowired
    public SimpleProducer<VerificationCodeMessage> getVerificationCodeProducer(){
        Destination destination = new ActiveMQTopic(verifyTopic);
        SimpleProducer simpleProducer = new SimpleProducer<VerificationCodeMessage>();
        simpleProducer.setDestination(destination);
        simpleProducer.setDestinationName(destination.toString());
        simpleProducer.setJmsTemplate(jmsTemplate);
        return simpleProducer;
    }

    @Bean
    @Autowired
    public SimpleProducer<OrderMessage> getStartOrderProducer(){
        Destination destination = new ActiveMQTopic(startOrderTopic);
        SimpleProducer simpleProducer = new SimpleProducer<OrderMessage>();
        simpleProducer.setDestination(destination);
        simpleProducer.setDestinationName(destination.toString());
        simpleProducer.setJmsTemplate(jmsTemplate);
        return simpleProducer;
    }

    @Bean
    @Autowired
    public SimpleProducer<TaskMessage> getStartTaskProducer(){
        Destination destination = new ActiveMQTopic(startTaskTopic);
        SimpleProducer simpleProducer = new SimpleProducer<TaskMessage>();
        simpleProducer.setDestination(destination);
        simpleProducer.setDestinationName(destination.toString());
        simpleProducer.setJmsTemplate(jmsTemplate);
        return simpleProducer;
    }

}
