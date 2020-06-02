package com.peas.springboot.thread.designer.single;

public class User extends Thread {
    private final String myName;
    private final String myAddress;
    private final Gate gate;

    public User(String myName, String myAddress, Gate gate) {
        this.myName = myName;
        this.myAddress = myAddress;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(myName+" begin");
        while(true){
            this.gate.pass(myName,myAddress);
        }
    }

    public static void main(String[] args) {
        Gate gate = new Gate();
        User bj = new User("baby", "beijing", gate);
        User gz = new User("guangLao", "guangzhou", gate);
        User sh = new User("shangLao", "shanghai", gate);
        bj.start();
        gz.start();
        sh.start();

    }
}
