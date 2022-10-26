package com.spring.annotation.extend;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author cyl
 * @date 2022-10-25 10:28
 * @description @EventListener 注解的使用
 */
@Component
public class AnnotationOfEventListener {

    /**
     * 可以监听多个事件
     */
    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent applicationEvent) {
        System.out.println("AnnotationOfEventListener....@EventListener....监听事件：" + applicationEvent);
    }
}
