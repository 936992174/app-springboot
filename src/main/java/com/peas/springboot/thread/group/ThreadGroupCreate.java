package com.peas.springboot.thread.group;

public class ThreadGroupCreate {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg1 = new ThreadGroup("tg1");
        Thread t1 = new Thread(tg1,"t1"){
            @Override
            public void run() {
//                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
//                        break;
                    }
//                }
            }
        };
        tg1.setDaemon(true);
        t1.start();
        Thread.sleep(2000);
        System.out.println(tg1.isDestroyed());


//       ThreadGroup tg2 = new ThreadGroup(tg1,"tg2");
//       Thread t2 = new Thread(tg2,"t2"){
//           @Override
//           public void run() {
//               while(true){
//                   try {
//                       Thread.sleep(1000);
//                   } catch (InterruptedException e) {
//                       e.printStackTrace();
//                       break;
//                   }
//               }
//           }
//       };
//       t2.start();
//       tg1.setDaemon(true);
//        tg1.interrupt();

    }
}
