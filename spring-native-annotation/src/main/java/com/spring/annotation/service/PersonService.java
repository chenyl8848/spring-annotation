package com.spring.annotation.service;

import com.spring.annotation.dao.PersonDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author cyl
 * @date 2022-08-11 10:46
 * @description
 */
@Service
public class PersonService {

//    @Qualifier(value = "personDao") // 指定 bean 的名称
//    @Autowired // 按照类型优先匹配，Spring 自带注解
//    No qualifying bean of type 'com.spring.annotation.dao.PersonDao' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
//    @Autowired(required = false) // 表示容器钟有就装配，没有就不装配，也不报错
//    @Resource // 按照名称匹配 JSR250 规范，Java 中的注解
    @Inject // 功能和 @Autowired 一样，JSR330 规范，java.inject 中的注解，需要引入对应的依赖
    private PersonDao personDao;


    @Override
    public String toString() {
        return "PersonService{" +
                "personDao=" + personDao +
                '}';
    }
}
