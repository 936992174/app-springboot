package com.peas.springboot.load;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader("myClassLoader");
        Class<?> aClass = myClassLoader.loadClass("com.peas.springboot.load.SimpleObject");
        System.out.println(aClass.getClassLoader());
        Object o = aClass.newInstance();
        Method method = aClass.getMethod("hello");
        Object result = method.invoke(o);
        System.out.println(result);

//        new SimpleObject();
    }
}
