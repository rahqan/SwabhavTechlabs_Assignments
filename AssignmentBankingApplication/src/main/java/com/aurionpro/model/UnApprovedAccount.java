package com.aurionpro.model;

import java.sql.Timestamp;

public class UnApprovedAccount {
    private int accountId;
    private int userId;
    private String accountNumber;
    private String accountType;
    private String status;
    private Timestamp createdAt;
    private String customerName; 

    public UnApprovedAccount() {}

    public UnApprovedAccount(int accountId, int userId, String accountNumber, 
                             String accountType, String status, Timestamp createdAt, 
                             String customerName) {
        this.accountId = accountId;
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.status = status;
        this.createdAt = createdAt;
        this.customerName = customerName;
    }

    // Getters & Setters
    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "UnApprovedAccount{" +
                "accountId=" + accountId +
                ", userId=" + userId +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
