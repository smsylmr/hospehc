package com.ylzinfo.hospehc.utils;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import java.io.Serializable;

/**
 * @author linmr
 * @description: ActiveMQ工具类
 * @date 2019/6/10
 */
@Component
public class ActiveMQUtil {
    @Autowired
    @Qualifier("firstJmsTemplate")
    private JmsMessagingTemplate jmsTemplate;

    @Value("${activemq.queue}")
    private String queue;

    public void sendObjectMsg(final Serializable msg) {
        Destination mqQueue = new ActiveMQQueue(queue);
        jmsTemplate.convertAndSend(mqQueue,msg);
    }

    public void sendTextMsg(final String msg) {
        Destination mqQueue = new ActiveMQQueue(queue);
        jmsTemplate.convertAndSend(mqQueue, msg);
    }

}

