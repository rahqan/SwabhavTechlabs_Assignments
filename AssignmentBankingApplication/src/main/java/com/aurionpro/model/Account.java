package com.aurionpro.model;

import java.sql.Timestamp;

public class Account {
	private int accountId;
	private int userId;
	private String accountNumber;
	private String accountType; // SAVINGS / CURRENT
	private String status; // PENDING / ACTIVE / REJECTED / BLOCKED
	private Timestamp createdAt;
	private Timestamp approvedAt;
	private Integer approvedBy; // nullable
	private double balance;

	// For CURRENT accounts only
	private Double cashCreditAllowed;
	private Double cashCreditRemaining;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Double getCashCreditAllowed() {
		return cashCreditAllowed;
	}

	public void setCashCreditAllowed(Double cashCreditAllowed) {
		this.cashCreditAllowed = cashCreditAllowed;
	}

	public Double getCashCreditRemaining() {
		return cashCreditRemaining;
	}

	public void setCashCreditRemaining(Double cashCreditRemaining) {
		this.cashCreditRemaining = cashCreditRemaining;
	}

	public Account() {
	}

	public Account(int accountId, int userId, String accountNumber, String accountType, String status,
			Timestamp createdAt, Timestamp approvedAt, Integer approvedBy, double balance,
			Double cashCreditAllowed, Double cashCreditRemaining) {
		this.accountId = accountId;
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.status = status;
		this.createdAt = createdAt;
		this.approvedAt = approvedAt;
		this.approvedBy = approvedBy;
		this.balance = balance;
		this.cashCreditAllowed = cashCreditAllowed;
		this.cashCreditRemaining = cashCreditRemaining;
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

	public Timestamp getApprovedAt() {
		return approvedAt;
	}

	public void setApprovedAt(Timestamp approvedAt) {
		this.approvedAt = approvedAt;
	}

	public Integer getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}
	@Override
	public String toString() {
		return "Account{" +
				"accountId=" + accountId +
				", userId=" + userId +
				", accountNumber='" + accountNumber + '\'' +
				", accountType='" + accountType + '\'' +
				", status='" + status + '\'' +
				", balance=" + balance +
				", cashCreditAllowed=" + cashCreditAllowed +
				", cashCreditRemaining=" + cashCreditRemaining +
				'}';
	}
}