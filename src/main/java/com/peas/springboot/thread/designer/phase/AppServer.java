package com.peas.springboot.thread.designer.phase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppServer extends Thread{
    private int port;

    private static final  int DEFAULT_PORT = 12722;

    private volatile boolean start = true;

    private List<ClientHandler> clientHandlers = new ArrayList<>();

    private final static ExecutorService executor = Executors.newFixedThreadPool(10);

    ServerSocket socket;

    public AppServer(){
        this(DEFAULT_PORT);
    }

    public AppServer(int port){
        this.port = port;
    }


    @Override
    public void run() {
        try {
            this.socket = new ServerSocket(port);
            while (start){
                Socket client = socket.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                executor.submit(clientHandler);
                clientHandlers.add(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {//代码结束后关掉一些东西
            this.dispose();
        }
    }

    private void dispose() {
        clientHandlers.forEach(ClientHandler::close);
        executor.shutdown();
    }

    private void shutdown() throws IOException {
        this.start = false;
        this.interrupt();
        this.socket.close();

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        AppServer appServer = new AppServer();
        appServer.start();
        Thread.sleep(10000);
        appServer.shutdown();
    }
}
