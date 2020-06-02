package com.peas.springboot.thread.designer.observer;

public interface LifeCycleListener {
    public void onEvent(ObserverRunnable.RunnableEvent event);
}
