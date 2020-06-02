package com.peas.springboot.thread.base;

import java.util.Optional;

public class ThreadSimpleApi {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            Optional.of("hello").ifPresent(System.out::println);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        Optional.of(t.getName()).ifPresent(System.out::println);
        Optional.of(t.getId()).ifPresent(System.out::println);
    }
}
