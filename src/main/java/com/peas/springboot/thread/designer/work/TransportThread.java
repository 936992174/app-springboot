package com.peas.springboot.thread.designer.work;

import java.util.Random;

public class TransportThread extends  Thread {
    private Channel channel;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public TransportThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            Request request = new Request(getName(), i);
            this.channel.put(request);
            try {
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
