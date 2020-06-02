package com.peas.springboot.thread.designer.suspension;

import java.util.Random;
import java.util.stream.IntStream;

public class ClientThread extends Thread {

    private RequestQueue requestQueue;

    private final Random random;

    private final String sendValue;

    public ClientThread(RequestQueue requestQueue, String sendValue) {
        this.requestQueue = requestQueue;
        this.sendValue = sendValue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        IntStream.range(0,20).forEach((i)->{
            System.out.println("client send value: "+sendValue);
            requestQueue.putRequest(new Request(sendValue));
//            try {
//                Thread.sleep(random.nextInt(1000));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });
    }
}
