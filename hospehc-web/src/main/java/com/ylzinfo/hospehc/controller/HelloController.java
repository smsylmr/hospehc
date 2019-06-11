package com.ylzinfo.hospehc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ylzinfo.hospehc.pojo.Account;
import com.ylzinfo.hospehc.service.AccountService;
import com.ylzinfo.hospehc.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @Reference(version = "1.0.0", filter = "traceIdFilter")
    private HelloService helloService;
    @Reference(version = "1.0.0", filter = "traceIdFilter")
    private AccountService accountService;

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello")
    public String hello() {
        Account account = accountService.queryById(1);
        String hello = helloService.sayHello(account.getUserName());
        log.info("----------------------------------------------------");
        log.info(hello);
        return hello;
    }
}
