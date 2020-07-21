package com.peas.springboot.lookup;

import org.springframework.beans.factory.annotation.Lookup;

/**
 * @Description
 * @Author 7287
 * @Date 2020/7/16 10:44
 * @Version V1.0
 **/
public abstract class LookUpTest {
    @Lookup(value = "student")
    public abstract User getUser();

    public void showName(){
        getUser().showName();
    }
    public static void main(String[] args) {

    }
}
