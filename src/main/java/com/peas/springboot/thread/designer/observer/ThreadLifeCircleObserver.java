package com.peas.springboot.thread.designer.observer;

import java.util.Arrays;
import java.util.List;

/**
 * 线程状态改变通知观察者
 */
public class ThreadLifeCircleObserver implements LifeCycleListener {

    private final Object LOCK = new Object();
    public void conCurrentQuery(List<String> ids){
        ids.forEach(id ->{
           new Thread(new ObserverRunnable(this) {
               @Override
               public void run() {
                   try {
                       //回调观察者方法
                       notifyChange(new RunnableEvent(RunnableState.RUNNING,Thread.currentThread(),null));
                       System.out.println("query for the id："+id);
                       Thread.sleep(1000);
                       notifyChange(new RunnableEvent(RunnableState.DONE,Thread.currentThread(),null));
                   } catch (InterruptedException e) {
                       notifyChange(new RunnableEvent(RunnableState.ERROR,Thread.currentThread(),e));
                   }
               }
           },id).start();
        });
    }

    @Override
    public void onEvent(ObserverRunnable.RunnableEvent event) {
        synchronized (LOCK){
            System.out.println("the runnable ["+Thread.currentThread().getName()+"] data changed and state is "+event.getState());
            if(event.getCause()!=null){
                System.out.println("the runnable ["+Thread.currentThread().getName()+"] process failed");
                event.getCause().printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new ThreadLifeCircleObserver().conCurrentQuery(Arrays.asList("1","2"));
    }
}
