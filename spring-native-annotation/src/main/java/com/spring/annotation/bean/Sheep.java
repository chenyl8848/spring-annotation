package com.spring.annotation.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author cyl
 * @date 2022-08-16 10:31
 * @description
 */
@Component
public class Sheep {

    public Sheep() {
        System.out.println("sheep...construct...");
    }

    // 对象创建并赋值之后调用
    @PostConstruct
    public void init() {
        System.out.println("sheep...init...");
    }

    // 容器移除对象之前调用
    @PreDestroy
    public void destroy() {
        System.out.println("sheep...destroy...");
    }
}
