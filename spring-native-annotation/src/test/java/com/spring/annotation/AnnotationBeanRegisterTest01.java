package com.spring.annotation;

import com.spring.annotation.bean.Person;
import com.spring.annotation.config.MainConfigOfBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cyl
 * @date 2022-08-11 10:46
 * @description 通过注解向 ioc 容器注册 bean 测试类
 */
public class AnnotationBeanRegisterTest01 {

    // 通过注解创建 spring 上下文
    ApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfBean.class);

    @Test
    public void testAnnotationRegisterBean() {
        // 获取 bean 实例
        Person person = (Person) context.getBean("person");

        System.out.println(person);

        // 输出
        // Person{name='李四', sex='女', age=24}
    }

    @Test
    public void testAnnotationComponentScan() {
        // 获取 ioc 容器中所有注册的 bean 实例
        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }

        // 输出
        // org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        // org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        // org.springframework.context.annotation.internalCommonAnnotationProcessor
        // org.springframework.context.event.internalEventListenerProcessor
        // org.springframework.context.event.internalEventListenerFactory
        // mainConfig
        // personController
        // personDao
        // personService
        // person
    }

    @Test
    public void testAnnotationScope() {
        Person person01 = (Person) context.getBean("person");
        Person person02 = (Person) context.getBean("person");
        System.out.println(person01 == person02); // true -- @Scope(value = "singleton")
        System.out.println(person01 == person02); // false -- @Scope(value = "prototype")
    }

}
