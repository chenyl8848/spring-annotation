package com.spring.annotation.custom;

import com.spring.annotation.bean.Color;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author cyl
 * @date 2022-08-15 11:52
 * @description
 */
public class ColorFactoryBean implements FactoryBean {

    /**
     * 返回一个 Color 对象，这个对象会添加到容器中
     */
    @Override
    public Color getObject() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("ColorFactoryBean...getObject...");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        // TODO Auto-generated method stub
        return Color.class;
    }

    //是单例？
    //true：这个bean是单实例，在容器中保存一份
    //false：多实例，每次获取都会创建一个新的 bean；
    @Override
    public boolean isSingleton() {
        // TODO Auto-generated method stub
        return true;
    }

}
