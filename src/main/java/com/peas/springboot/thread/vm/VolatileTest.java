package com.peas.springboot.thread.vm;

public class VolatileTest {

    private volatile static int INIT_VALUE = 0;
    private final static  int MAX_LIMIT = 5;

    public static void main(String[] args) {
        new Thread(()->{
            int localValue = INIT_VALUE;
            while(localValue<MAX_LIMIT){
                if(localValue!=INIT_VALUE){
                    System.out.printf("the value update to [%d]\n",INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        },"READER").start();

        new Thread(()->{
            int localValue = INIT_VALUE;
            while(INIT_VALUE<MAX_LIMIT){
                System.out.printf("update the value to [%d]\n",++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"update").start();
    }
}
