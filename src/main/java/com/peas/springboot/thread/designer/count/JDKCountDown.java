package com.peas.springboot.thread.designer.count;

import java.util.stream.IntStream;

/**
 * 等所有线程执行完再进行下阶段的事情
 */
public class JDKCountDown {
    private final int total;
    private int counter;

    public JDKCountDown(int total) {
        this.total = total;
    }

    public void down(){
        synchronized (this){
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this){
            while(counter!=total){
                this.wait();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final JDKCountDown latch = new JDKCountDown(5);

        IntStream.rangeClosed(1,5).forEach(i->{
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" is working");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.down();
            }).start();
        });
        latch.await();
        System.out.println("----------第二阶-----------");
    }
}
