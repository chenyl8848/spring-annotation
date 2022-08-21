package com.spring.annotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author cyl
 * @date 2022-08-21 21:16
 * @description
 */
//@Component
public class Car implements ApplicationContextAware {

    // 通过实现 ApplicationContextAware 接口拿到 IOC 容器
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
