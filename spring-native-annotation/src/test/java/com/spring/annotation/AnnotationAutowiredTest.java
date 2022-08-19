package com.spring.annotation;

import com.spring.annotation.config.MainConfig06;
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
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig06.class);

        PersonService personService = applicationContext.getBean(PersonService.class);

        System.out.println(personService);
    }
}
