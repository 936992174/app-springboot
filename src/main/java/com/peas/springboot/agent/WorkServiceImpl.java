package com.peas.springboot.agent;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * @Description
 * @Author 7287
 * @Date 2020/7/20 17:30
 * @Version V1.0
 **/
public class WorkServiceImpl implements WorkService {

    @Override
    public void work() {
        System.out.println("i am working");
    }
}
