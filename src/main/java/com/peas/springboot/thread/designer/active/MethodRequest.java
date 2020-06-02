package com.peas.springboot.thread.designer.active;

/**
 * 对应activeObject每一个方法
 */
public abstract class MethodRequest {
    protected final Servant servant;

    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();

}
