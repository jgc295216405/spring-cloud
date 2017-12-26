package com.netease.cloud.test.consumer.feign;

import com.netease.cloud.test.common.model.Student;
import com.netease.cloud.test.consumer.hystrix.FallbackService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//使用公共api的方式调用,所以此处先注释掉
@Primary
@FeignClient(value = "SERVICE",fallback = FallbackService.class)
public interface FeignService extends com.netease.cloud.test.common.api.FeignService{
//      @RequestMapping(value = "/student",method = RequestMethod.GET)
//      Student getStudentByName(@RequestParam("name")String name);
//      @RequestMapping(value = "/、student/{name}",method = RequestMethod.GET)
//      Student getStudentByNamePath(@、PathVariable("name")String name);
//      @RequestMapping(value = "/student/、post",method = RequestMethod.POST)
//      Student getStudentByStudent(@RequestBo、dy Student student);
}


