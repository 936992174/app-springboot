package com.peas.springboot.thread.Lock;

public class ReadWriteLock {
    /**
     * 正在读
     */
    private int readingReaders = 0;
    /**
     * 正在等待读
     */
    private int waitingReaders = 0;
    /**
     * 正在写
     */
    private int writingWriters = 0;
    /**
     * 正在等待写
     */
    private int waitingWriters = 0;

    private boolean preferWriter = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    public synchronized void readLock() throws InterruptedException {
            this.waitingReaders++;//正在等待读的+1
        try {
            while(writingWriters>0 || (preferWriter && waitingWriters>0)){
                this.wait();
            }
            this.readingReaders++;//没人写了，正在读的+1
        } finally {
            this.waitingReaders--;//正在等-1
        }
    }

    public synchronized void readUnLock(){
        this.readingReaders--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try{
            while(writingWriters>0 || readingReaders>0){
                wait();
            }
            this.writingWriters++;
        }finally {
            this.waitingWriters--;
        }

    }

    public synchronized void writeUnLock(){
        this.writingWriters--;
        this.notifyAll();
    }


    public static void main(String[] args) {
        ShareData shareData = new ShareData(10);
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();
        new ReadWorker(shareData).start();
        new WriterWorker(shareData,"hello key").start();
        new WriterWorker(shareData,"hello key").start();
    }

}
