package com.spring.annotation;

import com.spring.annotation.aop.MathCalculator;
import com.spring.annotation.config.MainConfigOfAOP;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.MathContext;

/**
 * @author cyl
 * @date 2022-08-22 9:05
 * @description aop 注解方式测试类
 */
public class AnnotationAopTest {

    @Test
    public void testAfterReturn() {
        // 创建 ioc 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);

        mathCalculator.div(10, 1);

        // 输出
        // div 运行...@Before...参数列表:{[10, 1]}
        // MathCalculator...div...
        // div 运行...@AfterReturning...参数列表:{[10, 1]}...返回值:{10}
        // div 运行...@After...参数列表:{[10, 1]}
    }

    @Test
    public void testAfterThrow() {
        // 创建 ioc 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);

        mathCalculator.div(10, 0);

        // 输出
        // div 运行...@Before...参数列表:{[10, 0]}
        // MathCalculator...div...
        // div 运行...@AfterThrowing...参数列表:{[10, 0]}...异常信息:{java.lang.ArithmeticException: / by zero}
        // div 运行...@After...参数列表:{[10, 0]}

    }
}
