package com.peas.springboot.thread.designer.balk;

public class Customer extends Thread {
    private final BalkingData balkingData;

    public Customer(BalkingData balkingData) {
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
//        synchronized (balkingData){
            int i = 0;
            while (true){
//                while(balkingData.isChanged()){
//                    try {
//                        balkingData.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
                if(i++>20)
                    break;
                balkingData.change(Thread.currentThread().getName());
//                balkingData.notifyAll();
            }

//        }
    }
}
