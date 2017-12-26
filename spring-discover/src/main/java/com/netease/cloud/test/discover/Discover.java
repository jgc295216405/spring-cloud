package com.netease.cloud.test.discover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Discover {
    public static void main(String[] args) {
        SpringApplication.run(Discover.class,args);
    }
}
