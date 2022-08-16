package com.spring.annotation.config;

import com.spring.annotation.bean.Person;
import com.spring.annotation.custom.MyComponentScanFilter;
import com.spring.annotation.service.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author cyl
 * @date 2022-08-11 10:50
 * @description 主配置类
 */
// 表示该类是一个配置类
@Configuration
// 包扫描注解
@ComponentScan(
        // 指定包扫描的路径
        value = "com.spring.annotation",
        // 设置基础包路径
//         basePackages = {"com.spring.annotation"},
//        basePackageClasses = {PersonController.class, PersonService.class},
        // 排除不要扫描的类
//        excludeFilters = {
//                @ComponentScan.Filter(
//                    // 按照注解类型进行排除
//                    type = FilterType.ANNOTATION,
//                    // 排除 @Controller 注解标注的类
//                    classes = Controller.class),
//                @ComponentScan.Filter(
//                    // 按照给定的类型进行排除
//                    type = FilterType.ASSIGNABLE_TYPE,
//                    // 排除 PersonService 类
//                    classes = {PersonService.class}
//                ),
//                @ComponentScan.Filter(
//                    // 按照正则进行排除
//                    type = FilterType.REGEX,
//                    // 正则表达式
//                    pattern = {"dao"}
//                ),
//                @ComponentScan.Filter(
//                    // 按照自定义规则排除
//                    type = FilterType.CUSTOM,
//                    // 要匹配的自定义规则
//                    classes = {MyComponentScanFilter.class}
//                )
//        },
        // 指定扫描的类
        includeFilters = {
                @ComponentScan.Filter(
                        // 按照注解类型进行扫描
                        type = FilterType.ANNOTATION,
                        // 排除 @Controller 注解标注的类
                        classes = Controller.class),
                @ComponentScan.Filter(
                        // 按照给定的类型进行扫描
                        type = FilterType.ASSIGNABLE_TYPE,
                        // 排除 PersonService 类
                        classes = {PersonService.class}
                ),
                @ComponentScan.Filter(
                        // 按照正则进行扫描
                        type = FilterType.REGEX,
                        // 正则表达式
                        pattern = {"dao"}
                ),
                @ComponentScan.Filter(
                        // 按照自定义规则扫描
                        type = FilterType.CUSTOM,
                        // 要匹配的自定义规则
                        classes = {MyComponentScanFilter.class}
                )
        },
        // 禁用默认的扫描规则
        useDefaultFilters = false
)
// 指定多个@ComponentScan
@ComponentScans(value = {@ComponentScan(), @ComponentScan()})
public class MainConfig01 {

    /**
     * @Bean 向 ioc 容器注册 bean
     * bean 的 id 默认就是 方法名
     * value/name 属性可以指定 bean 的名称
     */
    @Bean
    /**
     * @Scope 指定 bean 的作用域
     * ConfigurableBeanFactory.SCOPE_SINGLETON：单例
     * ConfigurableBeanFactory.SCOPE_PROTOTYPE：多例
     * org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST：同一次请求
     * org.springframework.web.context.WebApplicationContext.SCOPE_SESSION：同一个会话级别
     */
//    @Scope(value = "singleton")
    @Scope(value = "prototype")
    public Person person() {
        return new Person("李四", "女", 24);
    }
}
