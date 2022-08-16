package com.spring.annotation.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author cyl
 * @date 2022-08-16 10:28
 * @description
 */
@Component
public class Dog implements InitializingBean, DisposableBean {

    public Dog() {
        System.out.println("dog...construct...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("dog...afterPropertiesSet...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("dog...destroy...");
    }
}
