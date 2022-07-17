package com.revature.model;

import java.time.*;

public class Account {

    //the account object holds its id, balance and a current view object for filtering the display by month
    private int accountId;
    private double balance;
    public LocalDate currentViewDate = LocalDate.now();

    public void Account(){}

    public Account(int i, double v) {
        accountId = i;
        balance = v;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double myBalance) {
        balance = myBalance;
    }

    public LocalDate getCurrentViewDate() {
        return currentViewDate;
    }

    //used to change the month
    public void setCurrentViewDate(LocalDate currentViewDate) {
        this.currentViewDate = currentViewDate;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
