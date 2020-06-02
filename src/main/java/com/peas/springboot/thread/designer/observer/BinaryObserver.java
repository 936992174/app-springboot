package com.peas.springboot.thread.designer.observer;

public class BinaryObserver extends Observer {

    protected BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("binary string:"+Integer.toBinaryString(subject.getState()));
    }
}
