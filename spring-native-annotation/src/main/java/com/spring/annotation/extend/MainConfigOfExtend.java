package com.spring.annotation.extend;

import com.spring.annotation.bean.Orange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author cyl
 * @date 2022-09-15 9:02
 * @description 扩展配置类
 */
@Configuration
@ComponentScan(basePackages = { "com.spring.annotation.extend" })
public class MainConfigOfExtend {

    /**
     * 扩展原理：
     *  1.BeanPostProcessor:bean 后置处理器，bean 创建对象初始化前后进行拦截工作、
     *  2.BeanFactoryPostProcessor:BeanFactory 的后置处理器，在 beanFactory 标准初始化之后调用，来定制和修改 beanFactory 的内容；
     *      所有 bean 定义已经保存加载到 beanFactory,但是 bean 的实例还未创建　　　　　　
     *  3.BeanFactoryPostProcessor 原理：
     *      1。IOC 容器创建
     *      2.invokeBeanFactoryPostProcessors(beanFactory):
     *          如何找到所有的 BeanFactoryPostProcessor 并执行他们的方法？
     *              1.直接在 beanFactory 中找到所有类型是 BeanFactoryPostProcessor 的组件，并执行他们的方法
     *              2.在初始化创建其他组件前面执行
     *  4.BeanDefinitionRegistryPostProcessor:
     *      1.继承 BeanPostProcessor
     *      2.postProcessBeanDefinitionRegistry:在所有 bean 定义信息将要被加载，bean 实例还未创建的时候执行
     *      3。优先于 BeanFactoryPostProcessor 执行
     *      4.可以利用 BeanDefinitionRegistryPostProcessor 给容器再额外添加一些组件
     *      5.原理：
     *          1.IOC 容器创建
     *          2.refresh() --> invokeBeanFactoryPostProcessors(beanFactory)
     *          3.从容器中获取所有到所有的 BeanDefinitionRegistryPostProcessor:
     *              String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
     *          4.依次执行所有的 postProcessBeanDefinitionRegistry()
     *              invokeBeanDefinitionRegistryPostProcessors --> postProcessBeanDefinitionRegistry
     *          5.再来从容器中找到所有 BeanFactoryPostProcessor 组件，然后依次执行 postProcessBeanFactory()
     *  5.ApplicationListener:监听容器中发布的事件，事件驱动模型开发
     *      1.public interface ApplicationListener<E extends ApplicationEvent> extends EventListener:监听 ApplicationEvent 及其子类的事件
     *      2.使用步骤：
     *          1.写一个监听器（ApplicationListener 实现类）来监听某个事件（ApplicationEvent 及其子类）
     *              1.也可以使用 @EventListener 注解来监听事件发布
     *              2.原理：使用 EventListenerMethodProcessor 处理器来解析方法上的 @EventListener 注解
     *          2.把监听器加入到容器中
     *          3.只要容器中有相关事件的发布，监听器就能监听到这个事件
     *          4.发布一个事件
     *      3.执行流程：
     *          ContextRefreshedEvent、AnnotationExtendTest$1[source=hello world]、ContextClosedEvent
     *          1.IOC 容器创建
     *          2.refresh()
     *          3.finishRefresh():容器刷新完成会发布 ContextRefreshedEvent 事件
     *          4.执行自己发布的事件
     *          5.IOC 容器关闭会发布 ContextClosedEvent 事件
     *      4.原理：
     *           1.IOC 容器创建
     *           2.refresh()
     *           3.finishRefresh():容器刷新完成会发布 ContextRefreshedEvent 事件
     *           4.publishEvent(new ContextRefreshedEvent(this));
     *              1.获取事件的多播放器（派发器） getApplicationEventMulticaster()
     *              2.执行派发事件方法 multicastEvent(applicationEvent, eventType)
     *              3.获取到所有的 ApplicationListener: for (ApplicationListener<?> listener : getApplicationListeners(event, type))
     *                  1.如果有 Executor，可以支持使用 Executor 进行异步派发
     *                      if (executor != null) {
     * 				            executor.execute(() -> invokeListener(listener, event));
     *                        }
     *                  2.否则，同步的方式直接执行 invokeListener() 方法，拿到 listener 回调 ApplicationEvent 方法
     *      5.事件多播器（派发器）
     *          1.容器创建对象:refresh()
     *          2.initApplicationEventMulticaster():初始化 ApplicationEventMulticaster
     *              1.先去容器中找有没有 id="applicationEventMulticaster" 的组件
     *              2.如果没有就：this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
     *                  并且加入到容器中：beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);
     *                  这样就可以在其他组件要派发事件时，自动注入这个 applicationEventMulticaster
     *      6.容器中有哪些监听器
     *          1.容器创建：refresh()
     *          2.registerListeners();
     *              从容器中拿到所有的监听器，把他们注册到 ApplicationEventMulticaster 中
     *              String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
     * 		        for (String listenerBeanName : listenerBeanNames) {
     * 			        getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
     *              }
     *          3.
     *      7.SmartInitializingSingleton 原理：
     *          1.容器创建并 refresh()
     *          2.finishBeanFactoryInitialization(beanFactory); 初始化剩下的单实例 bean
     *              1.先创建所有的单实例 bean: getBean()
     *              2.获取所有创建好的单实例 bean,判断是否是 SmartInitializingSingleton 类型的，如果是就调用 afterSingletonsInstantiated()
     */

    @Bean
    public Orange orange() {
        return new Orange();
    }


}
