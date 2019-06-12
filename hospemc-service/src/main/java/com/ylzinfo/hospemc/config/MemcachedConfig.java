package com.ylzinfo.hospemc.config;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linmr
 * @description: xmemcached
 * @date 2019/6/10
 */
@Configuration
public class MemcachedConfig {

    /**
     * 服务器
     */
    @Value("${memcached.server}")
    private String server;

    /**
     * 操作超时时间，可以被API覆盖
     */
    @Value("${memcached.opTimeout}")
    private Integer opTimeout;
    /**
     * 连接池大小
     */
    @Value("${memcached.poolSize}")
    private Integer poolSize;

    /**
     * 是否开启失败模式
     */
    @Value("${memcached.failureMode}")
    private boolean failureMode;


    private Logger log = LoggerFactory.getLogger(getClass());

    @Bean(name = "memcachedClientBuilder")
    public MemcachedClientBuilder getBuilder() {
        MemcachedClientBuilder memcachedClientBuilder = new XMemcachedClientBuilder(server);

        // 内部采用一致性哈希算法
        memcachedClientBuilder.setSessionLocator(new KetamaMemcachedSessionLocator());
        // 操作的超时时间
        memcachedClientBuilder.setOpTimeout(opTimeout);
        // 采用二进制传输协议（默认为文本协议）
        memcachedClientBuilder.setCommandFactory(new BinaryCommandFactory());
        // 设置连接池的大小
        memcachedClientBuilder.setConnectionPoolSize(poolSize);
        // 是否开起失败模式
        memcachedClientBuilder.setFailureMode(failureMode);
        return memcachedClientBuilder;
    }

    /**
     * 由Builder创建memcachedClient对象，并注入spring容器中
     * @param memcachedClientBuilder
     * @return
     */
    @Bean(name = "memcachedClient")
    public MemcachedClient getClient(@Qualifier("memcachedClientBuilder") MemcachedClientBuilder memcachedClientBuilder) {
        MemcachedClient client = null;
        try {
            client =  memcachedClientBuilder.build();
        } catch(Exception e) {
            log.info("exception happens when bulid memcached client{}",e.toString());
        }
        return client;
    }



}