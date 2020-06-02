package com.peas.springboot.thread.Lock;

import java.util.stream.IntStream;

public class ShareData {
    private final char[] buffer;

    private final ReadWriteLock lock = new ReadWriteLock();

    public ShareData(int size) {
        this.buffer = new char[size];
        for (int i = 0;i<buffer.length;i++){
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try{
            lock.readLock();
            return doRead();
        }finally {
            lock.readUnLock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            doWrite(c);
        }finally {
            lock.writeUnLock();
        }
    }

    private void doWrite(char c) {
        IntStream.range(0,buffer.length).forEach(i -> {
                buffer[i] = c;
                slowly(10);
            }
        );
    }

    private char[] doRead() {
        char[] newBuff = new char[buffer.length];
        IntStream.range(0, buffer.length).forEach(i -> newBuff[i] = buffer[i]);
        slowly(50);
        return newBuff;
    }

    private void slowly(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

}
