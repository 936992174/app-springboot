package com.peas.springboot.thread.designer.balk;

import java.io.IOException;

public class ServerThread extends Thread{

    private final BalkingData balkingData;

    public ServerThread(BalkingData balkingData) {
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
//        synchronized (balkingData){
            while (true){
//                while(!balkingData.isChanged()){
//                    try {
//                        balkingData.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
                try {
                    balkingData.save();
//                    balkingData.notifyAll();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            }

        }
    }
}
