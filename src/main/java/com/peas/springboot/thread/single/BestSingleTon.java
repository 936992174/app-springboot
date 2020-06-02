package com.peas.springboot.thread.single;

public class BestSingleTon {
    private BestSingleTon(){}

    private static class InstanceHolder{
        private final static BestSingleTon instance = new BestSingleTon();
    }

    public static BestSingleTon getInstance(){
        return InstanceHolder.instance;
    }
}
