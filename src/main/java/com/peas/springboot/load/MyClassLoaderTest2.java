package com.peas.springboot.load;

public class MyClassLoaderTest2 {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader1 = new MyClassLoader("myClassLoader");
        MyClassLoader myClassLoader2 = new MyClassLoader("myClassLoader2");
//        myClassLoader2.setDir("E:\\");
        Class<?> aClass = myClassLoader1.loadClass("com.peas.springboot.load.SimpleObject");
        Class<?> aClass2 = myClassLoader2.loadClass("com.peas.springboot.load.SimpleObject");
        System.out.println(aClass.getClassLoader());
        System.out.println(aClass == aClass2);


    }
}
