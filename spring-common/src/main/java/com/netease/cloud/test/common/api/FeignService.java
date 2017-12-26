package com.netease.cloud.test.common.api;

import com.netease.cloud.test.common.model.Student;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 继承公共的api方式调用,从耦合度讲，不建议这么使用，
 */

public interface FeignService {
      @RequestMapping(value = "/student",method = RequestMethod.GET)
      Student getStudentByName(@RequestParam("name") String name);
      @RequestMapping(value = "/student/{name}",method = RequestMethod.GET)
      Student getStudentByNamePath(@PathVariable("name") String name);
      @RequestMapping(value = "/student/post",method = RequestMethod.POST)
      Student getStudentByStudent(@RequestBody Student student);
}
