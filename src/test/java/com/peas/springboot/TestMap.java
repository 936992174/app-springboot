package com.peas.springboot;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.function.Predicate;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMap {

    private Logger logger = LoggerFactory.getLogger(MyFirstApplicationTest.class);
    private int i;

    @Test
    public void testMap() {
        byte a = (byte)0xff;
        System.out.println(a);
        System.out.println("hello");
        System.out.println(new Date());
        System.out.println("....");
    }


}
