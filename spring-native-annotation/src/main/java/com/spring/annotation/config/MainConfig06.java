package com.spring.annotation.config;

import com.spring.annotation.dao.PersonDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author cyl
 * @date 2022-08-19 8:40
 * @description 配置类
 */
@Configuration
@ComponentScan(value = {"com.spring.annotation.controller", "com.spring.annotation.service", "com.spring.annotation.dao"})
public class MainConfig06 {

    /**
     * 自动装配：
     *  Spring 利用依赖注入（DI），完成对 IOC 容器中各个组件的依赖关系赋值
     *
     *  1.@Autowired注解：
     *      1.默认优先按照类型去容器中找对应的组件，找到就赋值
     *      2.如果找到多个相同类型的组件，再将属性的名称作为组件的 id 去容器中查找
     *      3.自动装配默认一定要将属性赋值好，没有就会报错 --> 可以使用@Autowired(required = false)
     *      4.Spring 定义的注解
     *  2.@Qualifier注解：指定需要装配的组件 id,而不是使用属性名
     *  3.@Primary注解：让 Spring 进行自动装配的时候，默认使用首选的组件，也可以继续使用 @Qualifier 注解指定需要装配的组件的名称
     *  4.@Resource注解：
     *      1.默认按照组件名称进行装配，不能支持 @Primary 注解，不能支持 @Autowired(required = false)
     *      2.属于 JSR250 规范，Java 自带的注解
     *  5.@Inject注解：
     *      1.需要导入 javax.inject 包，和 @Autowired 注解功能一样，不能支持 required = false
     *      2.属于 JSR330 规范
     *
     *
     */

    @Bean(value = "personDao2")
    @Primary // 指定 bean 优先注入
    public PersonDao personDao() {
        PersonDao personDao = new PersonDao();
        personDao.setLabel("2");
        return personDao;
    }
}
