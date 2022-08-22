package com.spring.annotation.aop;

/**
 * @author cyl
 * @date 2022-08-22 8:43
 * @description 数学运算
 */
public class MathCalculator {

    public int div(int i, int j) {
        System.out.println("MathCalculator...div...");

        return i / j;
    }
}
