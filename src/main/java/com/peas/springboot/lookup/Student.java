package com.peas.springboot.lookup;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author 7287
 * @Date 2020/7/16 10:43
 * @Version V1.0
 **/
@Component
public class Student extends User {
    public void showName(){
        System.out.println("student");
    }
}
