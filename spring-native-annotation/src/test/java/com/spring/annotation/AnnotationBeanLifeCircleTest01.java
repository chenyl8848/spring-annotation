package com.spring.annotation;

import com.spring.annotation.config.MainConfig04;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cyl
 * @date 2022-08-16 10:23
 * @description 注解方式配置 bean 的生命周期
 */
public class AnnotationBeanLifeCircleTest01 {

    /**
     * 当 bean 的作用域为 singleton 时，bean 在 ioc 容器时创建就初始化，在 ioc 容器关闭时销毁 bean
     * 当 bean 的作用域为 singleton 时，bean 在获取 bean 时才初始化
     */
    @Test
    public void testAnnotationBeanLfeCircle() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig04.class);

        Object cat = applicationContext.getBean("cat");

        // 容器移除 bean
        applicationContext.removeBeanDefinition("sheep");

        // 关闭 ioc 容器
        applicationContext.close();
    }
}
