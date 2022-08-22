package com.spring.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * @author cyl
 * @date 2022-08-22 8:45
 * @description 日志切面
 */
// 标注该类是个切面类
@Aspect
public class LogAspects {

    /**
     * 抽取公共的切入点表达式
     * 1.本类引用
     * 2.其他的切面引用
     */
    @Pointcut("execution(public int com.spring.annotation.aop.MathCalculator.*(..))")
    public void pointCut() {

    }

    // 在目标方法之前切入
    @Before(value = "pointCut()")
    public void logStart(JoinPoint joinPoint) {
        // 获取目标方法参数
        Object[] args = joinPoint.getArgs();
        // 获取目标方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + " 运行...@Before...参数列表:{" + Arrays.asList(args) + "}");
    }

    // 在目标方法之后切入
    @After(value = "com.spring.annotation.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        // 获取目标方法参数
        Object[] args = joinPoint.getArgs();
        // 获取目标方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + " 运行...@After...参数列表:{" + Arrays.asList(args) + "}");
    }

    // 在目标方法正确返回之后切入
    // JoinPoint 一定要出现在参数表的第一位
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logAfterReturn(JoinPoint joinPoint, Object result) {
        // 获取目标方法参数
        Object[] args = joinPoint.getArgs();
        // 获取目标方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + " 运行...@AfterReturning...参数列表:{" + Arrays.asList(args) + "}" + "...返回值:{" + result + "}");
    }

    // 在目标方法抛出异常之后切入
    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void logAfterThrow(JoinPoint joinPoint, Exception e) {
        // 获取目标方法参数
        Object[] args = joinPoint.getArgs();
        // 获取目标方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + " 运行...@AfterThrowing...参数列表:{" + Arrays.asList(args) + "}" + "...异常信息:{" + e + "}");
    }
}
