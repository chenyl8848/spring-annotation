package com.spring.annotation.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author cyl
 * @date 2022-08-11 10:26
 * @description person bean
 */
public class Person {

    @Value("张三")
    private String name;

    @Value("${person.sex}")
    private String sex;

    // 支持 SPEL
    @Value("#{100-88}")
    private Integer age;

    public Person() {
    }

    public Person(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
