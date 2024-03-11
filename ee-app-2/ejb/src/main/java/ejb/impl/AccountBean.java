package ejb.impl;

import core.Account;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateful;

//@Stateless
@Stateful

public class AccountBean implements Account {
    private  double accountBalance;


    @PostConstruct
    public void m() {
        System.out.println("account bean hashcode from post construct "+this.hashCode() + ".....");
    }

    @Override
    public void deposit(double amount) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("account bean 1 hashcode "+this.hashCode() + ".....");
        if (amount > 0) {
            accountBalance += amount;
            System.out.println("Deposit Amount : " + amount + " New balance : " + accountBalance);
        }

    }

    @Override
    public double getBalance() {
        return accountBalance;
    }


}
