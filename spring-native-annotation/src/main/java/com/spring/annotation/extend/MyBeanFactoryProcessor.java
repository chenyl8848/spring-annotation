package com.spring.annotation.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author cyl
 * @date 2022-09-15 9:03
 * @description
 */
@Component
public class MyBeanFactoryProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryProcessor.....BeanFactoryPostProcessor");

        int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        System.out.println("当前 beanFactory 中有 " + beanDefinitionCount + "个 bean");
        System.out.println(Arrays.asList(beanDefinitionNames));
    }
}
