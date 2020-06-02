package com.peas.springboot.thread.designer.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        if(this.state == state){
            return;
        }
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        observers.forEach(Observer::update);
    }
}
