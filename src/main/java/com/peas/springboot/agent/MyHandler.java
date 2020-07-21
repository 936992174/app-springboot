package com.peas.springboot.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description
 * @Author 7287
 * @Date 2020/7/20 17:32
 * @Version V1.0
 **/
public class MyHandler implements InvocationHandler {

    private Object target;

    public MyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(target, args);
        System.out.println("after");
        return result;
    }

    public <t> t getProxy() {
        return (t) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        WorkService proxy = new MyHandler(new WorkServiceImpl()).getProxy();
        proxy.work();
        System.out.println(System.getProperties().get("sun.misc.ProxyGenerator.saveGeneratedFiles"));

    }
}
