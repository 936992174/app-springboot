package com.peas.springboot.thread.base;

import static java.lang.Thread.sleep;

/**
 *  指定时间终止任务线程
 *  执行线程被中断后执行线程结束，守护线程（任务线程）也就结束了
 */
public class ForceInterrupt {
    private Thread executeThread;

    private boolean finished = false;

    public void execute(Runnable task){
        executeThread = new Thread(){
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();
                try {
                    runner.join();//守护线程执行中,
                    finished = true;
                } catch (InterruptedException e) {
                    System.out.println("执行线程被打断了。。。执行线程结束");
                }
            }
        };
        executeThread.start();
    }

    public void shutdown(int mills){
        long currentTime = System.currentTimeMillis();
        while(!finished){//执行线程尚未结束
            if(System.currentTimeMillis()-currentTime>=mills){//超时
                System.out.println("超时，需要结束");
                executeThread.interrupt();//执行线程中断，
                break;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断！");
                break;
            }
        }
        finished = false;
    }

    public static void main(String[] args) {
        ForceInterrupt forceInterrupt = new ForceInterrupt();
        long start = System.currentTimeMillis();
        forceInterrupt.execute(()->{
//            while (true){
//
//            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //超过时间就是终止任务线程
        forceInterrupt.shutdown(10000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
