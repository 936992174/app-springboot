package com.peas.springboot.thread.base;

public class DaemonThread {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            Thread innerThread = new Thread(()->{
                try {
                    while(true){
                        System.out.println("do something ");
                        Thread.sleep(1_000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            innerThread.setDaemon(true);
            innerThread.start();
            try {
                Thread.sleep(10_000);
                System.out.println("T thread finish done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        t.setDaemon(true);
        t.start();
    }
}