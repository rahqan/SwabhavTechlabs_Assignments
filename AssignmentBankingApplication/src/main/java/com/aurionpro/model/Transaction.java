package com.aurionpro.model;


import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private int fromAccountId;
    private String toAccountNumber;
    private String transactionType; // TRANSFER / DEPOSIT / WITHDRAWAL
    private double amount;
    private String description;
    private Timestamp transactionDate;
    private String status; // SUCCESS / FAILED / PENDING

    public Transaction() {}

    public Transaction(int transactionId, int fromAccountId, String toAccountNumber,
                       String transactionType, double amount, String description,
                       Timestamp transactionDate, String status) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountNumber = toAccountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
        this.status = status;
    }

    // Getters & Setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }
    public int getFromAccountId() { return fromAccountId; }
    public void setFromAccountId(int fromAccountId) { this.fromAccountId = fromAccountId; }
    public String getToAccountNumber() { return toAccountNumber; }
    public void setToAccountNumber(String toAccountNumber) { this.toAccountNumber = toAccountNumber; }
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Transaction{" + "transactionId=" + transactionId +
                ", fromAccountId=" + fromAccountId +
                ", toAccountNumber='" + toAccountNumber + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount + ", status='" + status + '\'' + '}';
    }
}
