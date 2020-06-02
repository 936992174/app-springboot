package com.peas.springboot.thread.designer.work;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;

public class Request {
    private final String name;

    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName()+" execute " + this);
    }

    @Override
    public String toString() {
        return "Request=>" +"No." + number  + ", name=" + name;
    }
}
