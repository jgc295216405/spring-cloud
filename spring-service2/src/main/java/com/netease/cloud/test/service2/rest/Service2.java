package com.netease.cloud.test.service2.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages = "com.netease.cloud.test.service2")
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class Service2 {
    public static void main(String[] args) {
        SpringApplication.run(Service2.class,args);
    }
    @Value("${server.port}")
    private int serverPort ;
    @GetMapping("/hello")
    public String test(String a){
        return "hello:"+a+",service port:"+serverPort;
    }
}
