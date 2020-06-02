package com.peas.springboot.bean;

public class TaxCalculator {
    private double salary;
    private double bonus;
    CalculatorStrategy strategy;

    public TaxCalculator(double salary, double bonus, CalculatorStrategy strategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.strategy = strategy;
    }

    public double calculate(){
        return strategy.calculate(salary,bonus);
    }
}
