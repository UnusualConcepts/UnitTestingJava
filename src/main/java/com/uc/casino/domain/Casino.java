package com.uc.casino.domain;

public class Casino {
    private int balance;

    public Casino(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public int sell(int amount) {
        balance -= amount;
        return amount;
    }
}
