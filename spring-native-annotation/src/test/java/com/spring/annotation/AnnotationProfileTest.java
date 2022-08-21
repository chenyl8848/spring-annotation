package com.spring.annotation;

import com.spring.annotation.bean.DataSource;
import com.spring.annotation.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cyl
 * @date 2022-08-21 22:03
 * @description 多环境配置测试类
 */
public class AnnotationProfileTest {

    // 通过命令：-Dspring.profiles.active = test 激活测试环境
    @Test
    public void testAnnotationProfile1() {
        // 创建 ioc 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);

        DataSource dataSource = applicationContext.getBean(DataSource.class);

        System.out.println(dataSource);
    }

    // 用代码的方式指定加载环境
    @Test
    public void testAnnotationProfile2() {

        // 1.创建 ioc 容器，使用无参构造器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 2.设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("prod");
        // 3.注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        // 4.启动容器并刷新
        applicationContext.refresh();
        // 5.获取 bean
        DataSource dataSource = applicationContext.getBean(DataSource.class);

        System.out.println(dataSource);


    }
}
