package com.peas.springboot.agent;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author 7287
 * @Date 2020/7/16 10:08
 * @Version V1.0
 **/
public class MyMethodInterceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置处理");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("后置处理");
        return object;
    }
}
