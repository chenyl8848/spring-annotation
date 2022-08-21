package com.spring.annotation.config;

import com.spring.annotation.bean.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author cyl
 * @date 2022-08-21 21:58
 * @description 主配置类
 */
@Configuration
public class MainConfigOfProfile {


    /**
     * profile：Spring 提供了多种环境，可以根据不同的环境，动态的激活和切换一系列组件
     *  如：开发环境、测试环境、生产环境等
     * @Profile 注解：指定组件在哪些环境下才能被注册到容器中，不指定，任何环境都能注册这个组件
     *  1.加了环境标识的 bean,只有这个环境被激活的时候才能注册到 ioc 容器中，默认是 default 环境
     *  2.写在配置类上，只有是指定环境的时候，整个配置类里面的所有配置才开始生效
     *  3.没有标注任何环境的 bean,在任何环境下都是加载的
     *  4.指定 Spring 环境的两种方式：
     *      1.通过命令：-Dspring.profiles.active = test 激活测试环境
     *      2.通过代码：
     *           // 1.创建 ioc 容器，使用无参构造器
     *           AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
     *           // 2.设置需要激活的环境
     *           applicationContext.getEnvironment().setActiveProfiles("prod");
     *           // 3.注册主配置类
     *           applicationContext.register(MainConfigOfProfile.class);
     *           // 4.启动容器并刷新
     *           applicationContext.refresh();
     * @return
     */

    // 开发环境
    @Bean
    @Profile(value = {"default", "dev"})
    public DataSource devDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl("dev");
        dataSource.setUserName("dev");
        dataSource.setPassword("dev");

        return dataSource;
    }

    // 测试环境
    @Bean
    @Profile(value = "test")
    public DataSource testDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl("test");
        dataSource.setUserName("test");
        dataSource.setPassword("test");

        return dataSource;
    }

    // 生产环境
    @Bean
    @Profile(value = "prod")
    public DataSource prodDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl("prod");
        dataSource.setUserName("prod");
        dataSource.setPassword("prod");

        return dataSource;
    }
}
