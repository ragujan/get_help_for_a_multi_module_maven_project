package ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface Tax {
    double calculate(double amount, double rate);
    void setValue(int value);

    int getValue();
}
