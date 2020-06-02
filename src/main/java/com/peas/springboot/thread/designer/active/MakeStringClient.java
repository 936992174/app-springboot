package com.peas.springboot.thread.designer.active;

public class MakeStringClient extends Thread {
    private final ActiveObject activeObject;

    private final char fillChar;


    public MakeStringClient(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillChar = name.charAt(0);
    }

    @Override
    public void run() {
        int i = 0;
        while(true){
            Result result = activeObject.makeString(i + 1, fillChar);
            i++;
            String resultValue = (String) result.getResultValue();
            System.out.println(Thread.currentThread().getName()+" value:"+resultValue);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
