package com.peas.springboot.thread.designer.change;

public class UserPersonThread extends Thread{

    private Person person;

    public UserPersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+":"+person.toString());
        }
    }

    public static void main(String[] args) {
        Person person = new Person("zhangsan","湖南");
        new UserPersonThread(person).start();
        new UserPersonThread(person).start();
    }
}
