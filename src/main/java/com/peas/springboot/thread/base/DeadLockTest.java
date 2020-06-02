package com.peas.springboot.thread.base;

public class DeadLockTest {
    public static void main(String[] args) {
        DeadService deadService = new DeadService();
        DeadLock deadLock = new DeadLock(deadService);
        deadService.setDeadLock(deadLock);
        new Thread(()->{
            while(true)
                deadService.s1();
        }).start();

        new Thread(()->{
            while(true)
                deadLock.m1();
        }).start();


    }
}
