package com.spring.annotation.config;

import com.spring.annotation.bean.Black;
import com.spring.annotation.bean.Red;
import com.spring.annotation.bean.White;
import com.spring.annotation.custom.ColorFactoryBean;
import com.spring.annotation.custom.MyImportBeanDefinitionRegistrar;
import com.spring.annotation.custom.MyImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author cyl
 * @date 2022-08-15 11:28
 * @description 主配置类
 */
@Configuration
// 导入组件，id 默认是全类名
@Import(value = {Red.class, Black.class, White.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfigOfImport {

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }

    /**
     * 给容器中注册组件；
     * 1）、包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）[自己写的类]
     * 2）、@Bean[导入的第三方包里面的组件]
     * 3）、@Import[快速给容器中导入一个组件]
     * 		1）、@Import(要导入到容器中的组件)；容器中就会自动注册这个组件，id 默认是全类名
     * 		2）、ImportSelector:返回需要导入的组件的全类名数组；
     * 		3）、ImportBeanDefinitionRegistrar:手动注册bean到容器中
     * 4）、使用 Spring 提供的 FactoryBean（工厂 Bean）;
     * 		1）、默认获取到的是工厂 bean 调用 getObject 创建的对象
     * 		2）、要获取工厂 Bean本身，我们需要给 id 前面加一个 &colorFactoryBean
     */
}
