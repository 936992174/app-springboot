package com.peas.springboot.thread.base;

import java.util.stream.IntStream;

public class JoinThread {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            IntStream.range(0,1000).forEach((item)->{
                System.out.println(Thread.currentThread().getName()+"->"+item);
            });
        });
        t.start();
        try {
            t.join();//插一脚
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IntStream.range(0,1000).forEach((item) -> {
            System.out.println(Thread.currentThread().getName()+"->"+item);
        });
    }
}
