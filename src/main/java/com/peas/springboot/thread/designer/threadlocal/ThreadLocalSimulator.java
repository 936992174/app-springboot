package com.peas.springboot.thread.designer.threadlocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalSimulator<T> {
    private final Map<Thread, T> storage = new HashMap<>();

    public void set(T t){
        synchronized (this){
            Thread thread = Thread.currentThread();
            storage.put(thread,t);
        }
    }

    public T get(){
        synchronized (this){
            Thread key = Thread.currentThread();
            return storage.get(key)==null?initialValue():storage.get(key);
        }
    }

    private T initialValue() {
        return null;
    }
}
