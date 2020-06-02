package com.peas.springboot.thread.base;

import java.util.Optional;
import java.util.stream.IntStream;

public class WaitSet {
    private static final Object LOCK = new Object();

    /**
     * 所有对象都会有一个wait set ，用来存放调用了该对象wait方法进入block状态的线程
     * 线程notify之后，不一定得到执行
     * 线程从wait set 被唤醒顺序不一致
     * 线程被唤醒后必须从新获取锁
     * @param args
     */
    public static void main(String[] args) {
        IntStream.rangeClosed(1,10).forEach(i->{
            new Thread(String.valueOf(i)){
                @Override
                public void run() {
                    synchronized (LOCK){
                        try {
                            Optional.of(Thread.currentThread().getName()+" will come to wait set").ifPresent(System.out::println);
                            LOCK.wait();
                            Optional.of(Thread.currentThread().getName()+" will leave to wait set").ifPresent(System.out::println);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IntStream.rangeClosed(1,10).forEach(i->{
            synchronized (LOCK){
                LOCK.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
