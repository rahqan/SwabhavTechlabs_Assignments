package com.aurionpro.model;



public class SavingsAccount {
    private int savingsId;
    private int accountId;
    private double balance;

    public SavingsAccount() {}

    public SavingsAccount(int savingsId, int accountId, double balance) {
        this.savingsId = savingsId;
        this.accountId = accountId;
        this.balance = balance;
    }

    // Getters & Setters
    public int getSavingsId() { return savingsId; }
    public void setSavingsId(int savingsId) { this.savingsId = savingsId; }
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "SavingsAccount{" + "savingsId=" + savingsId + ", accountId=" + accountId +
                ", balance=" + balance + '}';
    }
}

