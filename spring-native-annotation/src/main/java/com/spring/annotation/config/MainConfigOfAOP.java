package com.spring.annotation.config;

import com.spring.annotation.aop.LogAspects;
import com.spring.annotation.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author cyl
 * @date 2022-08-22 8:46
 * @description AOP 配置类
 */
@EnableAspectJAutoProxy // 开启 Aop 功能
@Configuration
public class MainConfigOfAOP {

    /**
     * 开启 AOP 功能三步骤:
     *  1.将业务逻辑组件和切面类都加入到 IOC 容器中，并告诉 Spring 那个是切面类(@Aspect)
     *  2.在切面类上的每一个通知方法标注通知注解，告诉 Spring 何时何地运行（切入点表达式）
     *  3.开启基于注解的 AOP 模式(@EnableAspectJAutoProxy)
     */

    /**
     * 流程：
     *  1.传入主配置类，创建 IOC 容器
     *  2.注册配置类，调用 refresh() 刷新容器
     *  3.registerBeanPostProcessors(beanFactory):注册 bean 的后置处理器来拦截 bean 的创建
     *      1.先获取 IOC 容器中已经定义了需要创建对象的所有 BeanPostProcessor
     *      2.给容器中加入别的 BeanPostProcessor
     *      3.优先注册实现了 PriorityOrdered 接口的 BeanPostProcessor
     *      4.再来注册实现了 Ordered 接口的 BeanPostProcessor
     *      5.最后注册没有实现优先级接口的 BeanPostProcessor
     *      6.注册 BeanPostProcessor,实际上就是创建 BeanPostProcessor 对象，保存在容器中
     *          创建 internalAutoProxyCreator 的 BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator】
     *          1.创建 bean 实例
     *          2.populateBean(beanName, mbd, instanceWrapper):给 bean 的各种属性赋值
     *          3.initializeBean(beanName, exposedObject, mbd):初始化 bean
     *              1.invokeAwareMethods():处理 Aware 接口的方法回调
     *              2.applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName):应用后置处理器的 postProcessBeforeInitialization
     *              3.invokeInitMethods(beanName, wrappedBean, mbd):执行自定义的初始化方法
     *              4.applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName):应用后置处理器的 postProcessAfterInitialization
     *          4.BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功
     *      7.把 BeanPostProcessor 注册到 beanFactory 中：beanFactory.addBeanPostProcessor(postProcessor);
     *  =============================以上是创建和注册 AnnotationAwareAspectJAutoProxyCreator 的过程=========================
     *  4.finishBeanFactoryInitialization(beanFactory):完成 beanFactory 初始化工作，创建剩下的单实例 bean
     *      1.遍历获取容器中的所有 bean,依次创建对象 getBean(beanName)
     *          getBean() --> doGetBean() -->  getSingleton()
     *      2.创建 bean
     *          【AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截，InstantiationAwareBeanPostProcessor，会调用postProcessBeforeInstantiation()】
     *          1.先从缓存中获取当前 bean,如果能获取到，说明 bean 是之前创建过的，直接使用，否则再创建，创建好的 bean 都会被缓存起来
     *          2.创建 bean: createBean(beanName, mbd, args)
     *              AnnotationAwareAspectJAutoProxyCreator 会在任何 bean 创建之前先尝试返回 bean 的实例
     *              【BeanPostProcessor 是在 bean 对象创建完成初始化前后调用的】
     *              【InstantiationAwareBeanPostProcessor 是在 bean 实例之前先尝试用后置处理器返回对象的】
     *              1.resolveBeforeInstantiation(beanName, mbdToUse):解析 BeforeInstantiation,
     *                  希望后置处理器在此能返回一个代理对象，如果能返回代理对象就使用，如果不能就继续 doCreateBean()
     *                  1.后置处理器先尝试返回对象
     *                      1.applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
     *                          1.拿到所有的后置处理器，如果是 InstantiationAwareBeanPostProcessor 就执行
     *                              postProcessBeforeInstantiation(beanClass, beanName)
     *                          2.postProcessBeforeInstantiation(beanClass, beanName)
     *                              1.advisedBeans:判断当前 bean 是否在 advisedBeans 中（保存了所有需要增强的 bean）
     *                              2.isInfrastructureClass:判断当前 bean 是否是基础类型的 Advice、Pointcut、Advisor、
     *                                  AopInfrastructureBean、或者是否是切面（@Aspect）
     *                              3.shouldSkip:判断是否需要跳过
     *                                  1.获取候选的增强器（切面里面的通知方法）：List<Advisor> candidateAdvisors
     *                                      每一个封装的通知方法的增强器是 InstantiationModelAwarePointcutAdvisor
     *                                      判断每一个增强器是否是 AspectJPointcutAdvisor 类型，返回 true
     *                                  2.永远返回 false
     *                      2.applyBeanPostProcessorsAfterInitialization(bean, beanName);
     *                          1.wrapIfNecessary(bean, beanName, cacheKey)
     *                              1.获取当前 bean 的所有增强器（通知方法）
     *                                  1.找到候选的所有增强器（找哪些通知方法需要切入当前方法的）
     *                                  2.获取到能在 bean 使用的增强器
     *                                  3.给增强器排序
     *                              2.保存当前 bean 在 advisedBeans 中
     *                              3.如果当前 bean 需要增强，创建当前 bean 的代理对象（createProxy）
     *                                  1.获取所有的增强器（通知方法）
     *                                  2.保存到 proxyFactory
     *                                  3.创建代理对象：JdkDynamicAopProxy(JDK 动态代理)、ObjenesisCglibAopProxy(cglib 动态代理)
     *                              4.给容器中返回当前组件使用 cglib 增强了代理的对象
     *                              5.以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
     *                          2.
     *                  2.
     *              2.doCreateBean():真正的去创建一个 bean 实例，和 3.6 流程一样
     *              3.resolveBeforeInstantiation():
     *      3.目标方法执行：容器中保存了组件的代理对象（cglib 增强后的对象），这个对象保存了所有详细信息（比如：增强器、目标对象）
     *          1.CglibAopProxy.intercept():拦截目标方法执行
     *          2.根据 ProxyFactory 对象获取将要执行的目标方法的拦截器链
     *              List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
     *              1.List<Object> interceptorList 保存所有拦截器（5个） 一个默认的 ExposeInvocationInterceptor 和 4个增强器；
     *              2.遍历所有的增强器，将其转为 Interceptor: registry.getInterceptors(advisor);
     *              3.将增强器转为 List<MethodInterceptor>:
     *                  如果是 MethodInterceptor，直接加入到集合中
     *                  如果不是，使用 AdvisorAdapter 将增强器转为 MethodInterceptor
     *                  转换完成返回 MethodInterceptor 数组；
     *          3.如果没有拦截器链，直接执行目标方法;
     *              拦截器链（每一个通知方法又被包装为方法拦截器，利用 MethodInterceptor 机制）
     *          4.如果有拦截器链，把需要执行的目标对象、目标方法、
     *              拦截器链等信息传入创建一个 CglibMethodInvocation 对象，并调用 Object retVal =  mi.proceed()
     *          5.拦截器链的触发过程：
     *              1.如果没有拦截器执行执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（指定到了最后一个拦截器）执行目标方法
     *              2.链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后再来执行
     *                  拦截器链的机制，保证通知方法与目标方法的执行顺序
     *
     */

    /**
     * AOP 原理（看给容器中注册了什么组件，这个组件什么时候工作，这个组件的功能是什么）：
     *  1.@EnableAspectJAutoProxy
     *      1.@Import(AspectJAutoProxyRegistrar.class):给容器中导入 AspectJAutoProxyRegistrar
     *      2.利用 AspectJAutoProxyRegistrar 给容器中导入注册 bean:AnnotationAwareAspectJAutoProxyCreator(bean 名字：internalAutoProxyCreator)
     *          internalAutoProxyCreator = AnnotationAwareAspectJAutoProxyCreator
     *  2.AnnotationAwareAspectJAutoProxyCreator
     *      1.继承 AspectJAwareAdvisorAutoProxyCreator --> 继承 AbstractAdvisorAutoProxyCreator
     *          --> 继承 AbstractAutoProxyCreator --> 实现 SmartInstantiationAwareBeanPostProcessor（后置处理器，在 bean 初始化前后做的事）、BeanFactoryAware
     *      2.AbstractAutoProxyCreator
     *          1.setBeanFactory(BeanFactory beanFactory):
     *          2.后置处理器的逻辑：
     *      3.AbstractAdvisorAutoProxyCreator
     *          1.setBeanFactory(BeanFactory beanFactory):重写了父类的方法 --> initBeanFactory(ConfigurableListableBeanFactory beanFactory)
     *          2.initBeanFactory(ConfigurableListableBeanFactory beanFactory):
     *      4.AspectJAwareAdvisorAutoProxyCreator
     */

    /**
     * 总结：
     *  1.@EnableAspectJAutoProxy 开启AOP功能
     *  2.@EnableAspectJAutoProxy 会给容器中注册一个组件 AnnotationAwareAspectJAutoProxyCreator
     *  3.AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
     *  4.容器的创建流程：
     *      1.registerBeanPostProcessors() 注册后置处理器；创建 AnnotationAwareAspectJAutoProxyCreator 对象
     *      2.finishBeanFactoryInitialization() 初始化剩下的单实例bean
     *          1.创建业务逻辑组件和切面组件
     *          2.AnnotationAwareAspectJAutoProxyCreator 拦截组件的创建过程
     *          3.组件创建完之后，判断组件是否需要增强 是：切面的通知方法，包装成增强器（Advisor）;给业务逻辑组件创建一个代理对象（cglib）
     *  5.执行目标方法：
     *      1.代理对象执行目标方法
     *      2.CglibAopProxy.intercept():
     *          1.得到目标方法的拦截器链（增强器包装成拦截器 MethodInterceptor）
     *          2.利用拦截器的链式机制，依次进入每一个拦截器进行执行
     *          3.效果：
     *              1.正常执行：前置通知 --> 目标方法 --> 后置通知 --> 返回通知
     *              2.出现异常：前置通知 --> 目标方法 --> 后置通知 --> 异常通知
     */

    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }

}
