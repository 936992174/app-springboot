package com.peas.springboot.thread.designer.active;

public class DisplayClientThread extends Thread{
    private final ActiveObject activeObject;

    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        int i = 0;
        while(true){
            String text = Thread.currentThread().getName()+"=>"+i;
            activeObject.displayString(text);
            i++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
