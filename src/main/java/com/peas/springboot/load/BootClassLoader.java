package com.peas.springboot.load;

public class BootClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(System.getProperty("sun.boot.class.path"));
        Class<?> aClass = Class.forName("com.peas.springboot.load.SimpleObject");
        System.out.println(aClass.getClassLoader());
        System.out.println(aClass.getClassLoader().getParent());
    }
}
