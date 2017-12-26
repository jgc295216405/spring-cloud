package com.netease.cloud.test.consumer.hystrix;

import com.netease.cloud.test.common.model.Student;
import com.netease.cloud.test.consumer.feign.FeignService;
import org.springframework.stereotype.Component;

/**
 * 熔断处理
 */
@Component
public class FallbackService implements FeignService {
    @Override
    public Student getStudentByName(String name) {
        return new Student();
    }

    @Override
    public Student getStudentByNamePath(String name) {
        return new Student();
    }

    @Override
    public Student getStudentByStudent(Student student) {
        return new Student();
    }
}
