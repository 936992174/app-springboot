package com.peas.springboot.thread.designer.context;

public class QueryFromDBAction {

    public void execute(){
        try {
            Thread.sleep(1000);
            ActionContext.getInstance().getContext().setName("peas "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
