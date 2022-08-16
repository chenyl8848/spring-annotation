package com.spring.annotation;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cyl
 * @date 2022-08-16 10:08
 * @description xml 方式配置 bean 的生命周期
 */
public class XmlBeanLifeCircleTest {

    /**
     * 当 bean 的作用域为 singleton 时，bean 在 ioc 容器时创建就初始化，在 ioc 容器关闭时销毁 bean
     * 当 bean 的作用域为 singleton 时，bean 在获取 bean 时才初始化
     */
    @Test
    public void testBeanLifeCircle() {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        Object cat = applicationContext.getBean("cat");
        // 关闭 ioc 容器
        applicationContext.close();
    }

}
