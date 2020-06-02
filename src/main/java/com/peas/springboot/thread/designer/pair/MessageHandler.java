package com.peas.springboot.thread.designer.pair;

import java.util.Random;

public class MessageHandler {
    private final static Random random = new Random(System.currentTimeMillis());

    private void request(Message message){
        new Thread(()->{
            String value = message.getValue();
        }).start();
    }
}
