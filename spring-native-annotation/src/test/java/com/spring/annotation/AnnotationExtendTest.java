package com.spring.annotation;

import com.spring.annotation.extend.MainConfigOfExtend;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cyl
 * @date 2022-09-15 9:13
 * @description 注解扩展原理测试类
 */
public class AnnotationExtendTest {

    @Test
    public void testAnnotationBeanFactoryProcessor() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfExtend.class);

    }
}
