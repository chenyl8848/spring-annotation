package com.spring.annotation.custom;

import com.spring.annotation.bean.RainBow;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author cyl
 * @date 2022-08-15 11:44
 * @description
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry BeanDefinition 注册类，把所有需要添加到容器中的 bean，调用 BeanDefinitionRegistry.registerBeanDefinition 手动注册进来
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        boolean beanDefinition01 = registry.containsBeanDefinition("com.spring.annotation.bean.Red");
        boolean beanDefinition02 = registry.containsBeanDefinition("com.spring.annotation.bean.Black");

        if (beanDefinition01 && beanDefinition02) {
            // 指定 bean 定义信息
            BeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            // 注册一个 bean,指定 bean 名称
            registry.registerBeanDefinition("rainBow", beanDefinition);
        }
    }
}
