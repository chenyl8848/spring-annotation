package com.spring.annotation.extend;

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
     *      3.
     *  4.
     */


}
