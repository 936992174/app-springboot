package com.peas.springboot.thread.single;

/**
 * volatile保证内存可见性，有序性
 */
public class BestLazySingleTon {
    private static volatile BestLazySingleTon instance;

    private BestLazySingleTon(){}

    public static BestLazySingleTon getInstance() {
        if (instance == null){//下次有线程进来就不会加锁了
            synchronized (BestLazySingleTon.class) {
                if (instance == null){
                    instance = new BestLazySingleTon();
                }
            }
        }
        return  instance;
    }
}
