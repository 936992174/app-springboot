package com.peas.springboot.thread.designer.observer;

public class OctalObserver extends Observer {

    protected OctalObserver(Subject subject) {
        super(subject);
    }
    @Override
    public void update() {
        System.out.println("octal string:"+Integer.toOctalString(subject.getState()));
    }
}
