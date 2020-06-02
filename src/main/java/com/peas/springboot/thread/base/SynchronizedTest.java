package com.peas.springboot.thread.base;

public class SynchronizedTest {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            while(true){
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
