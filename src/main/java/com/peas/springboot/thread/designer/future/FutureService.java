package com.peas.springboot.thread.designer.future;

import javax.xml.transform.Result;
import java.util.function.Consumer;

public class FutureService {
    public <T> Future<T> submit(final FutureTask<T> task){
        AsynFuture<T> future = new AsynFuture<>();
        new Thread(()->{
            T call = task.call();
            future.done(call);
        }).start();
        return future;
    }

    public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer){
        AsynFuture<T> future = new AsynFuture<>();
        new Thread(()->{
            T result = task.call();
            // 未来的一个凭据
            future.done(result);
            // 通知调用方
            consumer.accept(result);
        }).start();
        return future;
    }
}
