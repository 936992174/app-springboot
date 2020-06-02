package com.peas.springboot.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "person")
@Validated
@Data
public class Person {

    private String name;
    private Integer age;
    private boolean isBoss;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> objects;
    private Dog dog;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isBoss=" + isBoss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", objects=" + objects +
                ", dog=" + dog +
                '}';
    }
}
