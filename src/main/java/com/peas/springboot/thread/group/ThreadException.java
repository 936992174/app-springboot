package com.peas.springboot.thread.group;

public class ThreadException {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            try {
                Thread.sleep(1000);
                int a = 10;
                int b = 0;
                int result = a/b;
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        t.setUncaughtExceptionHandler((t1, e) -> {
            System.out.println(e);
            System.out.println(t1);
        });
    }
}
