package com.peas.springboot.thread.designer.active;

import java.util.LinkedList;

public class ActivationQueue {
    private final static int MAX_METHOD_REQUEST_QUEUE_SIZE = 100;

    private final LinkedList<MethodRequest> methodQueue;

    public ActivationQueue() {
        this.methodQueue = new LinkedList<>();
    }

    public synchronized void put(MethodRequest request){
        while(methodQueue.size() >= MAX_METHOD_REQUEST_QUEUE_SIZE){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.methodQueue.addLast(request);
        this.notifyAll();
    }

    public synchronized MethodRequest take(){
        while(methodQueue.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MethodRequest methodRequest = this.methodQueue.removeFirst();
        this.notify();
        return methodRequest;
    }
}
