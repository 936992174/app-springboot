package com.peas.springboot.agent;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @Description
 * @Author 7287
 * @Date 2020/7/16 10:06
 * @Version V1.0
 **/
public class AgentTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloService.class);
        enhancer.setCallback(new MyMethodInterceptor());
        HelloService o = (HelloService) enhancer.create();
        o.sayHello();
    }
}
