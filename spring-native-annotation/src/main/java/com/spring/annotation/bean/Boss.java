package com.spring.annotation.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cyl
 * @date 2022-08-21 21:16
 * @description
 */
//@Component
public class Boss {

    private Car car;

    private Cat cat;

//    @Autowired
//    public Boss(Car car) {
//        this.car = car;
//    }

//    @Autowired // 只有一个有参构造器时，@Autowired 注解可以省略
    public Boss(Car car, Cat cat) {
        this.car = car;
        this.cat = cat;
    }

//    public Boss(Cat cat) {
//        this.cat = cat;
//    }

    public Car getCar() {
        return car;
    }

//    @Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                ", cat=" + cat +
                '}';
    }
}
