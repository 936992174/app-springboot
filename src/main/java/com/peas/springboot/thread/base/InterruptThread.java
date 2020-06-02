package com.peas.springboot.thread.base;

public class InterruptThread {
    public static void main(String[] args) {
        Thread task = new Thread(){
            @Override
            public void run() {
                while (true){
//                    System.out.println("任务线程");
                }
            }
        };
        task.start();

        Thread thread = Thread.currentThread();
        Thread interrupt = new Thread(){///中断线程
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                thread.interrupt();//中断主线程，任务线程
            }
        };
        interrupt.start();

        try {
            task.join();//任务线程执行中
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
