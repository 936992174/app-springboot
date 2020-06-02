package com.peas.springboot.thread.designer.balk;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.stream.IntStream;

public class BalkingData {
    private final String fileName;

    private String content;

    private boolean changed;

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public BalkingData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    public synchronized void change(String newContent){
        System.out.println("change");
        this.content = newContent;
        this.changed = true;
    }

    public synchronized void save() throws IOException {
        if(!changed){
            return;
        }
        doSave();
        this.changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName()+" do save ");
        try(Writer writer = new FileWriter(fileName, true)) {
            writer.write(content);
            writer.write("\n");
            writer.flush();
        }


    }

    public static void main(String[] args) {
        BalkingData aa = new BalkingData("aa.txt", "aa");

        IntStream.range(1,2).forEach(i->{
            new Customer(aa).start();
        });

        IntStream.range(1,2).forEach(i->{
            new ServerThread(aa).start();
        });
    }
}
