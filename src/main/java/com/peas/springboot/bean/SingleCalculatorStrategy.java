package com.peas.springboot.bean;

public class SingleCalculatorStrategy implements CalculatorStrategy{
    @Override
    public double calculate(double salary, double bonus) {
        return salary*0.1+bonus*0.15;
    }
}
