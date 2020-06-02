package com.peas.springboot.thread.designer.message;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Produce extends  Thread{
    private final MessageQueue messageQueue;

    private final static Random random = new Random(System.currentTimeMillis());

    private final static AtomicInteger counter = new AtomicInteger(0);

    public Produce(MessageQueue messageQueue, int seq) {
        super("生产者-"+seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
            while (true){
                try {
                    Message message = new Message("message-" + counter.getAndIncrement());
                    messageQueue.put(message);
                    System.out.println(Thread.currentThread().getName()+"put message "+message.getData());
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
