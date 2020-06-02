package com.peas.springboot.thread.designer.future;

public interface Future<T> {
    T get() throws InterruptedException;
}
