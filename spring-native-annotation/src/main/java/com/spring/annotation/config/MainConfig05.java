package com.spring.annotation.config;

import com.spring.annotation.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author cyl
 * @date 2022-08-17 9:20
 * @description 主配置类
 */
@Configuration
// 绑定要加载的配置文件
//@PropertySource(value = {"classpath:person.properties"})
@PropertySources(value = {@PropertySource(value = {"classpath:person.properties"})})
public class MainConfig05 {

    @Bean
    public Person person() {
        return new Person();
    }
}
