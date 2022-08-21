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
@ComponentScan(value = {"com.spring.annotation.bean", "com.spring.annotation.custom"})
public class MainConfigOfBeanLifeCircle {

    /**
     * bean 的生命周期：
     *  bean 创建 --> bean 初始化 --> bean 销毁
     *
     * 容器管理 bean 的生命周期：
     *  可以自定义初始化和销毁方法：容器在 bean 进行到当前生命周期的时候调用自定义的初始化和销毁方法
     *
     * bean 创建：
     *  单实例：在容器启动时就创建对象
     *  多实例：在每次获取的时候创建对象
     *
     * bean 初始化：
     * BeanPostProcessor.postProcessBeforeInitialization(bean 初始化前调用) --> 初始化（对象创建完成后，并赋值好，调用初始化方法）
     *  --> BeanPostProcessor.postProcessAfterInitialization
     *
     * bean 销毁：
     *  单实例：容器关闭的时候销毁
     *  多实例：容器不会管理这个 bean,容器不会调用销毁方法
     *
     */

    /**
     * BeanPostProcessor 源码：
     *  populateBean(beanName, mbd, instanceWrapper):给bean进行属性赋值
     *  initializeBean
     *  {
     *      applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
     *      invokeInitMethods(beanName, wrappedBean, mbd):执行自定义初始化
     *      applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
     *  }
     *  遍历得到容器中所有 BeanPostProcessor,依次执行 postProcessBeforeInitialization,一旦返回 null,跳出 for 循环
     */

    /**
     * 1. 指定初始化和销毁方法；
     *  通过@Bean指定init-method和destroy-method；
     * 2. 通过让Bean实现InitializingBean（定义初始化逻辑）、DisposableBean（定义销毁逻辑）;
     * 3. 可以使用JSR250；
     *  @PostConstruct：在bean创建完成并且属性赋值完成；来执行初始化方法
     *  @PreDestroy：在容器销毁bean之前通知我们进行清理工作
     * 4.BeanPostProcessor【interface】：bean的后置处理器；
     *  在bean初始化前后进行一些处理工作；
     *  postProcessBeforeInitialization:在初始化之前工作
     *  postProcessAfterInitialization:在初始化之后工作
     *
     * Spring底层对 BeanPostProcessor 的使用；bean赋值，注入其他组件，@Autowired，生命周期注解功能，@Async,xxx BeanPostProcessor;
     *
     */

    @Scope(value = "prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Cat cat() {
        return new Cat();
    }
}
