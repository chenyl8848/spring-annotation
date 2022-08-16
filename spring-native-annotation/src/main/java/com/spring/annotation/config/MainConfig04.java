package com.spring.annotation.config;

import com.spring.annotation.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author cyl
 * @date 2022-08-16 10:03
 * @description 主配置类
 */
@Configuration
@ComponentScan(value = "com.spring.annotation.bean")
public class MainConfig04 {

    @Scope(value = "prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Cat cat() {
        return new Cat();
    }
}
