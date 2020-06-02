package com.peas.springboot.thread.single;

/**
 * 饿汉单例
 * 很长时间不使用，占用内存
 */
public class SingleTonObject1 {

    private static final SingleTonObject1 instance = new SingleTonObject1();

    private SingleTonObject1(){

    }

    public static SingleTonObject1 getInstance(){
        return instance;
    }

}
