package com.peas.springboot.thread.base;

import java.util.stream.Stream;

public class NotifyProduce {
    private int i = 0;

    private final Object object = new Object();

    private boolean beProduced = false;

    public void produce(){
        synchronized (object){
            if(beProduced){//有则等待
                try {
                    System.out.println(Thread.currentThread().getName()+"等待");
                    object.wait();
                    System.out.println(Thread.currentThread().getName()+"等待完后");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{//无则生成，通知消费者
                i++;
                System.out.println(Thread.currentThread().getName()+"生成"+i);
                beProduced = true;
                object.notifyAll();
            }
        }
    }

    public void consume(){
        synchronized (object){
            if(beProduced){//有则消费，通知生成者
                System.out.println(Thread.currentThread().getName()+"消费"+i);
                beProduced = false;
                object.notifyAll();
            }else{//无则等待
                try {
                    System.out.println(Thread.currentThread().getName()+"等待");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {//自己人唤醒自己人就睡了
        NotifyProduce notifyProduce = new NotifyProduce();
        Stream.of("p1","p2","p3").forEach(item ->{
            new Thread(()->{
                while(true)
                    notifyProduce.produce();
            },item).start();
        });

        Stream.of("c1").forEach(item -> new Thread(()->{
            while(true)
                notifyProduce.consume();
        },item).start());



    }
}
