package com.ylzinfo.hospehc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ylzinfo.hospehc.service.HelloService;
import org.springframework.stereotype.Component;

@Component
@Service(version = "1.0.0",interfaceName = "com.ylzinfo.hospehc.service.HelloService",filter = "traceIdFilter")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
