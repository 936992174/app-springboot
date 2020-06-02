package com.peas.springboot.thread.designer.message;

import java.util.LinkedList;

/**
 * 一个生产消息，一个消费消息
 */
public class MessageQueue {

    private final LinkedList<Message> queue;

    private final static int DEFAULT_MAX_LIMIT = 100;

    private final int limit;

    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(int limit) {
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    public void put(Message message) throws InterruptedException {
        synchronized (queue){
            while(queue.size()>limit){
                queue.wait();
            }
            queue.addLast(message);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (queue){
            while (queue.isEmpty()){
                queue.wait();
            }
            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }

    public int getMaxLimit(){
        return  this.limit;
    }

    public int getMessageSize() throws InterruptedException {
        synchronized (queue){
           return queue.size();
        }
    }

    public static void main(String[] args) {
        final MessageQueue messageQueue = new MessageQueue(100);
        new Produce(messageQueue,1).start();
        new Consumer(messageQueue,1).start();
    }
}
