package com.peas.springboot.load;

public class ClassActiveUse {

    public static void main(String[] args) throws ClassNotFoundException {
//        new Obj();
//        System.out.println(I.a);
//          Obj.salary = 100000L;
//          Obj.printSalary();
//        Class.forName("com.peas.springboot.load.Obj");
//        Child.printSalary();
        System.out.println(Obj.x);
    }
}

class Obj{
    public static long salary = 10000L;
    public static final int x = 10;
    static {
        System.out.println("obj 被初始化");
    }
    public static  void printSalary(){
        System.out.println("打印工资: "+salary);
    }
}

class Child extends Obj{
    public static int age = 26;
    static {
        System.out.println("child 被初始化");
    }
}

interface I {
    int a = 10;
}


