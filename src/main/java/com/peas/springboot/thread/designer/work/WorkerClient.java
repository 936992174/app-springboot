package com.peas.springboot.thread.designer.work;

public class WorkerClient {
    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.startWorker();
        new TransportThread("peas", channel).start();
        new TransportThread("jack", channel).start();
        new TransportThread("William", channel).start();
    }
}
