package com.ylzinfo.hospemc.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.ConnectionFactory;
import java.util.Properties;

/**
 * @author linmr
 * @description: ActiveMQ配置类
 * @date 2018/12/19
 */
@Configuration
@EnableJms
public class ActiveMQConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean(name = "firstConnectionFactory")
    public PooledConnectionFactory getFirstConnectionFactory(@Value("${activemq.url}") String brokerUrl,
                                                             @Value("${activemq.username}") String userName, @Value("${activemq.password}") String password)
    {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(userName);
        connectionFactory.setPassword(password);
        Properties props = new Properties();
        props.setProperty("prefetchPolicy.queuePrefetch", "1");
        connectionFactory.setProperties(props);
        connectionFactory.setTrustAllPackages(true);//信任所有包，否则在转换序列化对象时会抛异常
        PooledConnectionFactory poolFactory = new PooledConnectionFactory(connectionFactory);
        return poolFactory;
    }

    @Bean(name = "firstJmsTemplate")
    public JmsMessagingTemplate getFirstJmsTemplate(@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory) {
        JmsMessagingTemplate template = new JmsMessagingTemplate(connectionFactory);
        return template;
    }

    @Bean(name = "firstQueueListener")
    public DefaultJmsListenerContainerFactory getFirstQueueListener(@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory)
    {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        //设置并行消费者
        factory.setConcurrency("2");
        factory.setConnectionFactory(connectionFactory);
        // factory.setSessionAcknowledgeMode(4); // change acknowledge mode
        return factory;
    }
}

