package com.spring.annotation.bean;

import org.springframework.stereotype.Component;

/**
 * @author cyl
 * @date 2022-08-16 10:04
 * @description
 */
@Component
public class Cat {

    public Cat() {
        System.out.println("cat...construct...");
    }

    public void init() {
        System.out.println("cat...init...");
    }

    public void destroy() {
        System.out.println("cat...destroy..");
    }
}
