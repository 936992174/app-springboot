package com.peas.springboot.thread.single;

/**
 * 创建完后，其他线程使用发现没有初始化完成出现空指针异常
 */
public class LazySingleTon {
    private static LazySingleTon instance;

    private LazySingleTon(){}

    public static LazySingleTon getInstance(){
        if (instance == null){//下次有线程进来就不会加锁了
            synchronized (LazySingleTon.class){
                if (instance == null){
                    instance = new LazySingleTon();
                }
            }
        }
        return  instance;
    }
}
