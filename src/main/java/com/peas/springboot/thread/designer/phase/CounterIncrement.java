package com.peas.springboot.thread.designer.phase;

import javax.swing.*;
import java.util.Random;

/**
 * 执行中断后在final中任然执行一些操作
 */
public class CounterIncrement extends Thread {

    private volatile boolean terminated = false;

    private int counter = 0;

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while(!terminated){
                System.out.println(Thread.currentThread().getName()+" "+ counter++);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.clean();
        }
    }

    private void clean(){
        System.out.println("do some clean work for second phase, current counter "+counter);
    }

    public void close(){
        this.terminated = true;
        this.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        CounterIncrement counter = new CounterIncrement();
        counter.start();
        Thread.sleep(10_000);
        counter.close();
    }
}
