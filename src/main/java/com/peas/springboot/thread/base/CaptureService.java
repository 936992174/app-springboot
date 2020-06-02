package com.peas.springboot.thread.base;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 将任务指定给指定数量线程执行
 */
public class CaptureService {

    final static private LinkedList<Object> CONTROLS = new LinkedList<>();
    public static void main(String[] args) {
        List<Thread> workers = new ArrayList<>();
        Stream.of("M1","M2","M3","M4","M5","M6","M7","M8","M9","M10")
                .map(CaptureService::CreateCaptureThread)
                .forEach((t)->{
                    t.start();
                    workers.add(t);
                });
        workers.forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Optional.of("All of capture work finished").ifPresent(System.out::println);
    }

    private static Thread CreateCaptureThread(String name){
        return new Thread(()->{
            Optional.of("The worker ["+Thread.currentThread().getName()+"] begin capture data").ifPresent(System.out::println);
            synchronized (CONTROLS){
                while(CONTROLS.size()>=5){
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLS.addLast(new Object());
            }
            Optional.of("The worker ["+Thread.currentThread().getName()+"] is working").ifPresent(System.out::println);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (CONTROLS){
                Optional.of("The worker ["+Thread.currentThread().getName()+"] end capture data").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        },name);
    }
}
