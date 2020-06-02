package com.peas.springboot.thread.Lock;

import java.util.Collection;

public interface Lock {
     class TimeOutException extends Exception{
        public TimeOutException(String message){
            super(message);
        }
     }

     void lock() throws InterruptedException;
     void lock(long mills) throws InterruptedException, TimeOutException;
     void unLock();
     Collection<Thread> getBlockedThread();
     int getBlockedSize();
}
