package com.peas.springboot.thread.designer.threadlocal;

import java.util.Random;

public class ThreadLocalTest {
    private  static  ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "alex");
    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
//        threadLocal.set("alex");
//        System.out.println(threadLocal.get());
        Thread t1 = new Thread(() -> {
            threadLocal.set("Thread-t1");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            threadLocal.set("Thread-t2");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();

        t1.join();
        t2.join();

        System.out.println("=====");
        System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
    }
}
