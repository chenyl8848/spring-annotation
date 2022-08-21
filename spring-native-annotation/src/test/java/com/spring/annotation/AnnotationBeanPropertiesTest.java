package com.spring.annotation;

import com.spring.annotation.bean.Person;
import com.spring.annotation.config.MainConfigOfPropertySource;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cyl
 * @date 2022-08-17 9:22
 * @description 配置注解测试类
 */
public class AnnotationBeanPropertiesTest {

    @Test
    public void testAnnotationValue() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertySource.class);
        Person person = (Person) applicationContext.getBean("person");

        System.out.println(person);
        // 输出
        // Person{name='张三', sex='null', age=12}
    }

    @Test
    public void testAnnotationPropertySource() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertySource.class);
        Person person = (Person) applicationContext.getBean("person");

        System.out.println(person);
        // 输出
        // Person{name='张三', sex='woman', age=12}
    }
}
