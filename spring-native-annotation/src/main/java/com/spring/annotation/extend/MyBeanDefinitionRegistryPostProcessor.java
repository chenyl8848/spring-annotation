package com.spring.annotation.extend;

import com.spring.annotation.bean.Orange;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author cyl
 * @date 2022-10-25 9:59
 * @description
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    /**
     * BeanDefinitionRegistryPostProcessor 接口提供的方法，优先执行
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor.....postProcessBeanDefinitionRegistry");

        int beanDefinitionCount = registry.getBeanDefinitionCount();
        System.out.println("当前 beanFactory 中有 " + beanDefinitionCount + "个 bean");

        // 再往 IOC 容器中注册组件
        // 方式一
//        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Orange.class);
        // 方式二
        AbstractBeanDefinition rootBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Orange.class).getBeanDefinition();
        registry.registerBeanDefinition("hello", rootBeanDefinition);
    }

    /**
     * BeanFactoryPostProcessor 接口提供的方法，后执行
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor.....postProcessBeanFactory");

        int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
        System.out.println("当前 beanFactory 中有 " + beanDefinitionCount + "个 bean");

    }
}
