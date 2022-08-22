package com.spring.annotation.config;

import com.spring.annotation.aop.LogAspects;
import com.spring.annotation.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author cyl
 * @date 2022-08-22 8:46
 * @description AOP 配置类
 */
@EnableAspectJAutoProxy // 开启 Aop 功能
@Configuration
public class MainConfigOfAOP {

    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }

}
