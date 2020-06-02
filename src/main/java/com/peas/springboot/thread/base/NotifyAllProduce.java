package com.peas.springboot.thread.base;

import java.util.stream.Stream;

public class NotifyAllProduce {
    private int i = 0;

    private final Object object = new Object();

    private boolean beProduced = false;

    public void produce(){
        synchronized (object){
            while(beProduced){//使用while线程被唤醒后可以接着创建，用else可能就出去了，重新抢锁
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //无则生成，通知消费者
            i++;
            System.out.println(Thread.currentThread().getName()+"生成"+i);
            beProduced = true;
            object.notifyAll();
        }
    }

    public void consume(){
        synchronized (object){
            while(!beProduced){
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }///有则消费，通知生成者
            System.out.println(Thread.currentThread().getName()+"消费"+i);
            beProduced = false;
            object.notifyAll();
        }
    }

    public static void main(String[] args) {
        NotifyAllProduce notifyAllProduce = new NotifyAllProduce();
        Stream.of("c1","c2").forEach(item -> new Thread(()->{
            while(true)
                notifyAllProduce.consume();
        },item).start());

        Stream.of("p1","p2").forEach(item ->{
            new Thread(()->{
                while(true)
                    notifyAllProduce.produce();
            },item).start();
        });
    }
}
