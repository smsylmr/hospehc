package com.ylzinfo.hospemc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ylzinfo.hospemc.service.HelloService;
import org.springframework.stereotype.Component;

@Component
@Service(version = "1.0.0",interfaceName = "com.ylzinfo.hospemc.service.HelloService",filter = "traceIdFilter")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
