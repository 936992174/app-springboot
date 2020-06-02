package com.peas.springboot.bean;

public class TicketStation {
    public static  Integer total = 10;

    public static void main(String[] args) {
        for(int i=0;i<11;i++){
//            new TicketWindow("柜台"+i).start();
            final Runnable TicketWindow = ()->{
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("total:{}"+TicketStation.total+""+total--);
            };
        }
    }
}
