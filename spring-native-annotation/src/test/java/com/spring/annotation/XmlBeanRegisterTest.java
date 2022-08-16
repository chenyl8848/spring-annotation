package com.spring.annotation;

import com.spring.annotation.bean.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cyl
 * @date 2022-08-11 10:34
 * @description xml 文件向 ioc 容器注册 bean 测试类
 */
public class XmlBeanRegisterTest {

    // 通过 xml 配置文件创建 spring 上下文
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

    @Test
    public void testXmlRegisterBean() {
        // 获取 bean
        Person person = (Person) context.getBean("person");

        System.out.println(person);

        // 输出
        // Person{name='张三', sex='男', age=18}
    }

    @Test
    public void testXmlComponentScan() {
        // 获取 ioc 容器中所有注册的 bean 信息
        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }

        // 输出
        // person
        // personController
        // personDao
        // personService
        // org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        // org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        // org.springframework.context.annotation.internalCommonAnnotationProcessor
        // org.springframework.context.event.internalEventListenerProcessor
        // org.springframework.context.event.internalEventListenerFactory
    }


}
