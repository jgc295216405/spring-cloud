package com.netease.cloud.test.consumer.feign;

import com.netease.cloud.test.common.api.FeignService;
import com.netease.cloud.test.common.model.Student;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FeignController implements InitializingBean {
   // private static final Log LOG = Logger.getLogger(FeignController.class.getName());
    private static final Log log = LogFactory.getLog(FeignController.class);
    @Autowired
//    @Qualifier("feignService")
    private FeignService feignService;
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudent(@RequestParam String name) {
        log.info("call getStudent");
        return feignService.getStudentByName(name);
    }
    @RequestMapping(value = "/student/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudentPath(@PathVariable String name) {
        return feignService.getStudentByNamePath(name);
    }
    @RequestMapping(value = "/student/post", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudentByStudent(String name) {
        Student student=new Student();
        student.setName(name);
        return feignService.getStudentByStudent(student);
    }
    public void afterPropertiesSet() throws Exception {
        System.out.println();
    }
}
