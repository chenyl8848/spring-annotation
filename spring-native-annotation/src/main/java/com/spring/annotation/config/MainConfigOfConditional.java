package com.spring.annotation.config;

import com.spring.annotation.bean.Person;
import com.spring.annotation.custom.LinuxCondition;
import com.spring.annotation.custom.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author cyl
 * @date 2022-08-11 16:44
 * @description 主配置类
 */
// 表示该类是个配置类
@Configuration
// 满足当前条件，这个类中的配置的所有 bean 注册才能生效
//@Conditional(value = {WindowsCondition.class})
public class MainConfigOfConditional {

    @Conditional(value = WindowsCondition.class)
    @Bean("Bill")
    public Person person01() {
        return new Person("Bill Gates", "男", 60);
    }

    @Conditional(value = LinuxCondition.class)
    @Bean("Linus")
    public Person person02() {
        return new Person("Linus", "男", 48);
    }
}
