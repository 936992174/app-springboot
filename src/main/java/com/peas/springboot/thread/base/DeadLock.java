package com.peas.springboot.thread.base;

public class DeadLock{
    private final Object a = new Object();
    private DeadService service;

    public DeadLock(DeadService service) {
         this.service = service;
    }

    public void m1(){
        synchronized (a){//有a锁，需要b锁
            System.out.println("m1");
            service.s2();
        }
    }

    public void m2(){
        synchronized (a){
            System.out.println("m2");
        }
    }
}
