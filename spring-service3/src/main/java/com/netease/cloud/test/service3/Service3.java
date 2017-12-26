package com.netease.cloud.test.service3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class Service3 {
    @Value("${server.port}")
    private int serverPort ;
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String detail() {
        String memos = "I come form " + this.serverPort;
        return memos;
    }

    public static void main(String[] args) {
        SpringApplication.run(Service3.class,args);
    }
}
