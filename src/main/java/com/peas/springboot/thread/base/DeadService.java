package com.peas.springboot.thread.base;

public class DeadService {
    private final Object b = new Object();
    private DeadLock deadLock;

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    public void s1() {
        synchronized (b){//有b锁，需要a锁
            System.out.println("s1");
            deadLock.m2();
        }
    }

    public void s2(){
        synchronized (b){
            System.out.println("s2");
        }
    }

}
