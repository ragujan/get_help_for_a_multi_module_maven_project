package ejb.impl;

import ejb.remote.Tax;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;

@Stateless
public class TaxCalculateBean implements Tax {
    private int testvalue;
    @PostConstruct
    public void m() {
        System.out.println("from post construct ");
        System.out.println("serial code "+this.hashCode());
        System.out.println("..............");
    }

    @Override
    public double calculate(double amount, double rate) {
        System.out.println("calculate method "+this.hashCode());
        return (amount * rate);
    }

    @Override
    public void setValue(int value) {
        this.testvalue = value;
    }

    @Override
    public int getValue() {
        return this.testvalue;
    }
}
