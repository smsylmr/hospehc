package com.ylzinfo.hospemc;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableDubboConfiguration
@ServletComponentScan
public class DubboConsumerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(DubboConsumerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DubboConsumerApplication.class);
    }
}
