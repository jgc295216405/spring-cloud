package com.netease.cloud.test.service2.feign;



import com.netease.cloud.test.common.api.FeignService;
import com.netease.cloud.test.common.model.Student;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 继承了公共api所以把自己的方法注释了
 */
@RestController
public class FeignService2 implements InitializingBean,FeignService {
    private static Map<String,Student> studentMap=new HashMap<String, Student>(){
        {
            put("a",new Student(20,"a","北京"));
            put("b",new Student(21,"b","上海"));
            put("c",new Student(22,"c","广州"));
        }
    };

    /**
     * get 方式
     * RequestParam方式
     * @param name
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/student",method = RequestMethod.GET)
//    public Student getStudent(@RequestParam String name){
//        return studentMap.get(name);
//    }

    /**
     * get
     * PathVariable方式
     * @param name
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/student/{name}",method = RequestMethod.GET)
//    public Student getStudentPath(@PathVariable String name){
//        return studentMap.get(name);
//    }


    /**
     * post 方式
     * RequestBody
     * @param
     * @return
     */
//    @RequestMapping(value = "/student/post",method = RequestMethod.POST)
//    public Student getStudentByStudent(@RequestBody Student student){
//        return studentMap.get(student.getName());
//    }

    /**@RequestParam加不加均可
     * @param name
     * @return
     */
    @Override
    public Student getStudentByName( String name) {
        return studentMap.get(name);
    }

    @Override
    public Student getStudentByNamePath(@PathVariable String name) {
        return studentMap.get(name);
    }

    @Override
    public Student getStudentByStudent(@RequestBody Student student) {
        return studentMap.get(student.getName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println();
    }
}
