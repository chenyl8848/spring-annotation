package com.spring.annotation.custom;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author cyl
 * @date 2022-08-11 16:47
 * @description 操作系统为 Linux 时才生效
 */
public class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取 beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 获取环境信息
        Environment environment = context.getEnvironment();
        // 获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        // 获取 bean 注册表
        BeanDefinitionRegistry registry = context.getRegistry();

        // 获取操作系统
        String property = environment.getProperty("os.name");
        // 判断某个 bean 是否有注册
        boolean containsBeanDefinition = registry.containsBeanDefinition("person");

        if (property.contains("linux")) {
            return true;
        }
        return false;
    }
}
