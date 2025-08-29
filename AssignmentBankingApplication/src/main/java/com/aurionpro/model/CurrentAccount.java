package com.aurionpro.model;



public class CurrentAccount {
    private int currentId;
    private int accountId;
    private double balance;
    private double cashCreditAllowed;
    private double cashCreditRemaining;

    public CurrentAccount() {}

    public CurrentAccount(int currentId, int accountId, double balance,
                          double cashCreditAllowed, double cashCreditRemaining) {
        this.currentId = currentId;
        this.accountId = accountId;
        this.balance = balance;
        this.cashCreditAllowed = cashCreditAllowed;
        this.cashCreditRemaining = cashCreditRemaining;
    }

    // Getters & Setters
    public int getCurrentId() { return currentId; }
    public void setCurrentId(int currentId) { this.currentId = currentId; }
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public double getCashCreditAllowed() { return cashCreditAllowed; }
    public void setCashCreditAllowed(double cashCreditAllowed) { this.cashCreditAllowed = cashCreditAllowed; }
    public double getCashCreditRemaining() { return cashCreditRemaining; }
    public void setCashCreditRemaining(double cashCreditRemaining) { this.cashCreditRemaining = cashCreditRemaining; }

    @Override
    public String toString() {
        return "CurrentAccount{" + "currentId=" + currentId + ", accountId=" + accountId +
                ", balance=" + balance + ", cashCreditAllowed=" + cashCreditAllowed +
                ", cashCreditRemaining=" + cashCreditRemaining + '}';
    }
}
