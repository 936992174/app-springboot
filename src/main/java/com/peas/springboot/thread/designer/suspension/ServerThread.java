package com.peas.springboot.thread.designer.suspension;

import java.util.Random;

/**
 * 任务一个一个的执行
 */
public class ServerThread extends  Thread {
    private RequestQueue requestQueue;

    private Random random;

    private volatile boolean closed = false;

    public ServerThread(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!closed){
            Request request = requestQueue.getRequest();
            if(request == null){
                continue;
            }
            System.out.println("server get value: "+request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void close(){
        closed = true;
        this.interrupt();
    }

    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue,"alex").start();
        ServerThread serverThread = new ServerThread(requestQueue);
        serverThread.start();

    }
}
