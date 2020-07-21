package com.peas.springboot.agent;

import java.util.Arrays;

/**
 * @Description
 * @Author 7287
 * @Date 2020/7/20 15:08
 * @Version V1.0
 **/
public class Dog implements Animal {

    public static void main(String[] args) {
        Class<?>[] interfaces = Dog.class.getInterfaces();
        System.out.println(interfaces.length);
        Arrays.stream(interfaces).map(Class::getName).forEach(System.out::println);
    }
}
