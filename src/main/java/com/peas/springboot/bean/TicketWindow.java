package com.peas.springboot.bean;

public class TicketWindow extends Thread{

    private String name;

    public TicketWindow(String name) {
        this.name = name;
    }

    public void subTickets(int amount){
        TicketStation.total = TicketStation.total-amount;
    }

    @Override
    public void run() {
        while (true){
            synchronized (TicketWindow.class){
                if(TicketStation.total<1){
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subTickets(1);
                System.out.println(name+currentThread().getName()+",total:{}"+TicketStation.total);

            }
        }
    }

    public static void main(String[] args) {
        new TicketWindow("窗口").start();
    }
}
