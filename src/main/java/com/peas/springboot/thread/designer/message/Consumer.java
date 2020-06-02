package com.peas.springboot.thread.designer.message;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer extends Thread{
    private final MessageQueue messageQueue;

    private final static Random random = new Random(System.currentTimeMillis());

    private final static AtomicInteger counter = new AtomicInteger(0);

    public Consumer(MessageQueue messageQueue, int seq) {
        super("消费者-"+seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Message take = messageQueue.take();
                System.out.println(Thread.currentThread().getName()+"get message "+take.getData());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
