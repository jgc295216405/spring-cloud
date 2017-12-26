package com.netease.cloud.test.consumer.rest;

import com.netease.cloud.test.common.model.Student;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
/*
熔断监控
 */
@EnableHystrix
@EnableHystrixDashboard

/**
 * 熔断服务
 */
@EnableCircuitBreaker
/**
 *开启feign调用方式
 */
@EnableFeignClients({"com.netease.cloud.test.consumer"})
@EnableDiscoveryClient
@SpringBootApplication
@RestController
@ComponentScan(basePackages ={"com.netease.cloud.test.consumer"})
public class Consumer {
    private static  final String SERVICE="SERVICE";
    /**
     * 负载均衡方法1
     * @return
     */
    @Bean(name = "restTemplate1")
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Autowired
    @Qualifier("restTemplate1")
    private  RestTemplate restTemplate1;


    @HystrixCommand(fallbackMethod="helloFallBack")
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1() {
        return restTemplate1.getForEntity("http://"+SERVICE+"/hello?a=jgc", String.class).getBody();
    }

    public String helloFallBack() {
        return new Student(100,"jgc","default").toString();
    }
    /**
     * 负载均衡方法2
     */
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String hello2() {
        ServiceInstance serviceInstance=this.loadBalancerClient.choose(SERVICE);
        String host=serviceInstance.getHost();
        int port=serviceInstance.getPort();
        URI uri=URI.create(String.format("http://%s:%s/hello?a=jgc",host,port));
        return new RestTemplate().getForEntity(uri, String.class).getBody();
    }

    /**
     * 负载均衡方法3
     * @param
     */
    @Primary
    @Bean(name = "loadBalanceRestTemplate3")
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    @Autowired
    @Qualifier("loadBalanceRestTemplate3")
    private RestTemplate restTemplate3;
    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    public String hello3() {
        ServiceInstance serviceInstance=this.loadBalancerClient.choose(SERVICE);
        String host=serviceInstance.getHost();
        int port=serviceInstance.getPort();
        URI uri=URI.create(String.format("http://%s:%s/hello?a=jgc",host,port));
        return this.restTemplate3.getForEntity(uri, String.class).getBody();
    }
    public static void main(String[] args) {
        SpringApplication.run(Consumer.class,args);
    }
}
