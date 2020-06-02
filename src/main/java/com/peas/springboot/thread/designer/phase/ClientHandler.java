package com.peas.springboot.thread.designer.phase;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;

    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter pw = new PrintWriter(outputStream)) {
            while (running){
                String message = br.readLine();
                if(message==null)
                    break;
                System.out.println("com from client ->" +message);
                pw.write("echo ->"+message);
                pw.flush();
            }
        } catch (IOException e) {
            this.running = false;
        }finally {
            this.close();
        }
    }

    public void close(){
        if(!running){
            return;
        }
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {

        }
    }
}
