package com.spring.annotation;

import com.spring.annotation.bean.Person;
import com.spring.annotation.config.MainConfig01;
import com.spring.annotation.config.MainConfig02;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cyl
 * @date 2022-08-11 10:46
 * @description 通过注解向 ioc 容器注册 bean 测试类
 */
public class AnnotationBeanRegisterTest02 {

    // 通过注解创建 spring 上下文
    ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig02.class);

    @Test
    public void testAnnotationCondition() {
        // 获取 ioc 容器中所有注册的 bean 实例
        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

}
