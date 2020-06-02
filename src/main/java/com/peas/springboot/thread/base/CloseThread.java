package com.peas.springboot.thread.base;

public class CloseThread {

     static class WorkThread extends Thread{
         private volatile static  boolean state = true;

         @Override
         public void run() {
             while(true){
//                 try {//过一段时间就中断了
////                     Thread.sleep(1);
////                 } catch (InterruptedException e) {
////                     break;
////                 }
                 if(this.isInterrupted()){//线程中断就退出
                     break;
                 }
                 //中断后的一些操作。。。。。
             }
         }

         public void setState(){
             state = false;
         }
     }
    public static void main(String[] args) {
        WorkThread work = new WorkThread();
        work.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//等待1秒后中断耗时任务t
        work.interrupt();
    }
}
