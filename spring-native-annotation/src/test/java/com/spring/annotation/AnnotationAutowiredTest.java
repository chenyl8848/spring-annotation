package com.spring.annotation;

import com.spring.annotation.bean.Boss;
import com.spring.annotation.bean.Car;
import com.spring.annotation.config.MainConfigOfAutowired;
import com.spring.annotation.service.PersonService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cyl
 * @date 2022-08-19 8:43
 * @description 依赖注入测试类
 */
public class AnnotationAutowiredTest {

    @Test
    public void testAnnotationAutowired() {
        // 1.创建 ioc 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

        PersonService personService = applicationContext.getBean(PersonService.class);

        System.out.println(personService);
    }

    @Test
    public void testSetterAutowired() {
        // 创建 ioc 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

        Boss boss = applicationContext.getBean(Boss.class);
        Car car = applicationContext.getBean(Car.class);

        System.out.println(boss);

    }
}
