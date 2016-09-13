package com.ikamobile.jms.consumer;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageListener;
import javax.jms.Session;

/**
 * Created by Administrator on 2016/7/8.
 */
@Configuration
public class JmsConfigBean {

    @Value("${jms.pa.sms.module.verify.code.topic}")
    private String verifyCodeTopic;
    @Value("${jms.pa.sms.module.start.order.topic}")
    private String startOrderTopic;
    @Value("${jms.pa.sms.module.start.task.topic}")
    private String startTaskTopic;
    @Value("${jms.pa.sms.module.send.account.topic}")
    private String sendAccountTopic;


    @Value("${jms.pa.sms.module.verify.code.client}")
    private String verifyCodeClientId;
    @Value("${jms.pa.sms.module.start.order.client}")
    private String startOrderClientId;
    @Value("${jms.pa.sms.module.start.task.client}")
    private String startTaskClientId;
    @Value("${jms.pa.sms.module.send.account.client}")
    private String sendAccountClientId;

    @Bean
    public Destination getLoginCodeTopic(){
        return new ActiveMQTopic(verifyCodeTopic);
    }
    @Bean
    public Destination getOrderSuccessTopic(){
        return new ActiveMQTopic(startOrderTopic);
    }
    @Bean
    public Destination getAddVehicleTopic(){
        return new ActiveMQTopic(sendAccountTopic);
    }
    @Bean
    public Destination getTaskSuccessTopic(){
        return new ActiveMQTopic(startTaskTopic);
    }

    @Bean
    @Autowired
    public DefaultMessageListenerContainer loginCodeContainer(ConnectionFactory connectionFactory,
                                                              SendVerificationCodeListener sendVerificationCodeListener){
        return getContainer(verifyCodeClientId,connectionFactory,verifyCodeTopic,sendVerificationCodeListener);
    }
    @Bean
    @Autowired
    public DefaultMessageListenerContainer orderStartContainer(ConnectionFactory connectionFactory,
                                                               StartOrderListener startOrderListener){
        return getContainer(startOrderClientId,connectionFactory,startOrderTopic, startOrderListener);
    }
    @Bean
    @Autowired
    public DefaultMessageListenerContainer taskStartContainer(ConnectionFactory connectionFactory,
                                                                 StartTaskListener startTaskListener){
        return getContainer(startTaskClientId,connectionFactory,startTaskTopic, startTaskListener);
    }
    @Bean
    @Autowired
    public DefaultMessageListenerContainer addVehicleContainer(ConnectionFactory connectionFactory,
                                                               SendAccountListener sendAccountListener){
        return getContainer(sendAccountClientId,connectionFactory,sendAccountTopic,sendAccountListener);
    }


    private DefaultMessageListenerContainer getContainer(String clientId,ConnectionFactory connectionFactory,
                                                         String topic,MessageListener listener){
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestination(new ActiveMQTopic(topic));
        container.setClientId(clientId);
        //处理以下消息确认模式，当消息处理出现异常， 不向broker发确认，
        container.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        container.setSubscriptionDurable(true);
        container.setMessageListener(listener);
        return container;
    }

}
