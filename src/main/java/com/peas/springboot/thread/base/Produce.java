package com.peas.springboot.thread.base;

public class Produce {
    private final Object object = new Object();
    private  int i = 1;
    private void produce(){
        synchronized (object){
            System.out.println("p->"+i++);
        }
    }
    private void consume(){
        synchronized (object){
            System.out.println("c->"+i);
        }
    }

    public static void main(String[] args) {
        Produce produce = new Produce();
        new Thread(()->{
            while(true)
            produce.produce();
        },"p").start();

        new Thread(()->{
            while(true)
            produce.consume();
        },"c").start();
    }
}
