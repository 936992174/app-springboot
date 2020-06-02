package com.peas.springboot;

import com.peas.springboot.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyFirstApplicationTest {

    private Logger logger = LoggerFactory.getLogger(MyFirstApplicationTest.class);
    @Autowired
    Person person;
    @Test
    public void loadContext(){
        logger.trace("这是trace:{}","hello");
        logger.info(person.toString());
    }
}
