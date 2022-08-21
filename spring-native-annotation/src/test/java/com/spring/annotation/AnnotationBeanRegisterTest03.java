package com.spring.annotation;

import com.spring.annotation.config.MainConfigOfImport;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cyl
 * @date 2022-08-15 11:32
 * @description
 */
public class AnnotationBeanRegisterTest03 {

    private ApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfImport.class);

    @Test
    public void testAnnotationImport() {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void testColorFactotyBean() {
        Object bean01 = context.getBean("colorFactoryBean");
        Object bean02 = context.getBean("colorFactoryBean");
        Object bean03 = context.getBean("&colorFactoryBean");
        System.out.println(bean01.getClass());
        System.out.println(bean01 == bean02);
        System.out.println(bean03.getClass());
        // 输出
        // class com.spring.annotation.bean.Color
        // true
        // class com.spring.annotation.custom.ColorFactoryBean

    }
}
