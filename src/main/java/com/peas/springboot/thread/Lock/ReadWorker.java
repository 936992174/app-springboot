package com.peas.springboot.thread.Lock;

public class ReadWorker extends Thread {
    private final ShareData data;

    public ReadWorker(ShareData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true){
                char[] read = data.read();
                System.out.println(Thread.currentThread().getName()+": read :"+String.valueOf(read));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
