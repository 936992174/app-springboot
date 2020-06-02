package com.peas.springboot.thread.pool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 简单的线程池
 */
public class SingleThreadPool extends Thread{
    // 线程数量
    private int size;
    // 默认数量
    private final static int DEFAULT_SIZE = 10;
    // 队列数量
    private int queueSize;
    // 默认任务数量
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;
    //
    private static volatile int seq = 0;
    // 线程名
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";
    // 线程组名
    private final static ThreadGroup group = new ThreadGroup("pool");

    private final  DiscardPolicy  discardPolicy;

    private int min;

    private int max;

    private int active;

    private volatile boolean destroy = false;

    /**
     * 线程队列
     */
    private final static List<WorkTask>  THREAD_QUEUE = new ArrayList<>();
    /**
     * 任务队列
     */
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = ()->{
        throw new DiscardException("discard");
    };

    public SingleThreadPool() {
        this(4,8,12,DEFAULT_SIZE, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SingleThreadPool(int min, int active, int max, int size, int queueSize,  DiscardPolicy  discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.size = size;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        for(int i = 0;i<this.min;i++){//创建最小的线程数量的线程，放在线程池中
            createWorkTask();
        }
        this.size = min;
        this.start();
    }

    /**
     *
     * @param runnable
     */
    public void submit(Runnable runnable){
        if(destroy)
            throw new IllegalStateException("线程池不允许提交");
        synchronized (TASK_QUEUE){
            if(TASK_QUEUE.size()>queueSize)
                discardPolicy.discard();
            TASK_QUEUE.addLast(runnable);//任务队列添加任务
            TASK_QUEUE.notifyAll();//唤醒线程
        }
    }

    /**
     * 创建
     */
    private void createWorkTask(){
        WorkTask workTask = new WorkTask(group, THREAD_PREFIX + (seq++));
        workTask.start();
        THREAD_QUEUE.add(workTask);
    }

    public void shutdown() throws InterruptedException {
        while(!TASK_QUEUE.isEmpty()){
            Thread.sleep(1000);
        }
        synchronized (THREAD_QUEUE){//这边遍历的时候，其他线程不能去删除或增加
            int initVal = THREAD_QUEUE.size();
            while (initVal > 0) {
                for (WorkTask workTask : THREAD_QUEUE) {
                    if (workTask.taskState == TaskState.BLOCKED) {//将阻塞的关闭
                        workTask.interrupt();
                        workTask.close();
                        initVal--;
                    } else {
                        Thread.sleep(100);
                    }
                }
            }
        }
        this.destroy = true;
        System.out.println("关掉线程池");
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public boolean isDestroy(){
        return  this.destroy;
    }

    @Override
    public void run() {
        while(!isDestroy()){
            System.out.printf("pool#Min:%d,max:%d,current:%d,queueSize:%d\n",this.min,this.max,this.size,TASK_QUEUE.size());
            try {
                Thread.sleep(2000);
                if(TASK_QUEUE.size()>active && size<active){//任务队列数超过线程数，并且线程数小于可激活数
                    for (int i = size; i<active; i++){
                        createWorkTask();
                    }
                    size = active;
                    System.out.println("线程增加到active");
                }else if(TASK_QUEUE.size()>max && size<max){
                    for (int i = size;i<max;i++){
                        createWorkTask();
                    }
                    size = max;
                    System.out.println("线程增加到max");
                }
                synchronized (THREAD_QUEUE){
                    if(TASK_QUEUE.isEmpty() && size>active){//任务队列里没有任务了并且线程数大于active,需要释放到多余的线程
                        int releaseSize = size - active;
                        Iterator<WorkTask> it = THREAD_QUEUE.iterator();
                        while (it.hasNext()){
                            if(releaseSize<0)
                                break;
                            WorkTask task = it.next();
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size = active;
                    }
                    System.out.println("线程减少到active");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private enum TaskState{
        //空闲，运行，阻塞，死亡
        FREE, RUNNING, BLOCKED, DEAD
    }

    private static class WorkTask extends Thread{

        private  volatile TaskState taskState = TaskState.FREE;

        public TaskState getTaskState(){
            return this.taskState;
        }

        public void close(){
            this.taskState = TaskState.DEAD;
        }

        public WorkTask(ThreadGroup group, String name){
            super(group, name);
        }

        @Override
        public void run() {
            OUTER://唤醒打断
           while(this.taskState != TaskState.DEAD){//线程池中的线程不是死亡状态
                Runnable runnable;
                synchronized (TASK_QUEUE){
                    while(TASK_QUEUE.isEmpty()){//任务队列中没有任务，这个线程就等着
                        try {
                            this.taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }//有任务，则取出一个任务
                    runnable = TASK_QUEUE.removeFirst();
                }
               if(runnable != null){//任务队列中取出线程不为null,运行该线程run方法，耗时操作将其放到synchronized外面
                   this.taskState = TaskState.RUNNING;
                   runnable.run();
                   this.taskState = TaskState.FREE;
               }
           }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SingleThreadPool singleThreadPool = new SingleThreadPool(4,8,12,10,40,SingleThreadPool.DEFAULT_DISCARD_POLICY);

        IntStream.rangeClosed(0,40).forEach(i->{
                     singleThreadPool.submit(()->{
                         System.out.println("the runnable "+i+" serviced"+Thread.currentThread()+" start");
                         try {
                             Thread.sleep(3000);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                         System.out.println("the runnable "+i+" serviced"+Thread.currentThread()+" end");
                     });
                }
        );
        singleThreadPool.shutdown();
    }
}
