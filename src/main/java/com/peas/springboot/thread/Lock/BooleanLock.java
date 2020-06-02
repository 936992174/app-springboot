package com.peas.springboot.thread.Lock;

import java.util.*;
import java.util.stream.Stream;

/**
 * 自己的一把锁
 */
public class BooleanLock implements Lock {

    private  boolean initValue;
    private  Thread currentThread;
    private Collection<Thread> blockedThreadCollection = new HashSet<>(   );

    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while(initValue){//已经锁住，当前线程等待,直到执行unlock，又继续执行（等待中的线程抢占资源）
            blockedThreadCollection.add(Thread.currentThread());
            System.out.println(blockedThreadCollection);
            this.wait();
        }
        blockedThreadCollection.remove(Thread.currentThread());
        this.currentThread = Thread.currentThread();
        initValue = true;

    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if(mills <= 0){
            lock();
        }
        long hasRemain = mills;
        long endTime = System.currentTimeMillis() + mills;
        while(initValue){//锁住，等待直到有线程完成任务释放锁
            if(hasRemain <= 0)//等的时间超过了
                throw new TimeOutException("超时,hasRemain:"+hasRemain);
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemain = endTime - System.currentTimeMillis();
        }
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unLock() {
        if(currentThread == Thread.currentThread()){//对的线程
            this.initValue = false;
            System.out.println(Thread.currentThread().getName()+" release the lock monitor");
            this.notifyAll();
        }else {
            System.out.println(currentThread.getName()+":我不理你,"+Thread.currentThread().getName());
        }

    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return 0;
    }

    public static void main(String[] args) {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("t1","t2","t3","t4","t5").forEach((name)->
                new Thread(()->{
                    try {
//                        booleanLock.lock();
                        booleanLock.lock(20_000);
                        Optional.of(Thread.currentThread().getName()+" is working").ifPresent(System.out::println);
                        //执行任务
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }catch (TimeOutException e){
                        System.out.println(Thread.currentThread().getName()+" "+e.getMessage());
                    }finally {
                        booleanLock.unLock();
                    }
                },name).start());
    }
}
