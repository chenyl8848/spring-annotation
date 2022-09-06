package com.spring.annotation;

import com.spring.annotation.tx.MainConfigOfTX;
import com.spring.annotation.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cyl
 * @date 2022-09-05 9:04
 * @description
 */
public class AnnotationTXTest {

    @Test
    public void testInsert() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfTX.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.insertUser();
    }
}
