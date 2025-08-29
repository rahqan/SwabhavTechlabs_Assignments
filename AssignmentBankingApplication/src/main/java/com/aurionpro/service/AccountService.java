package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dao.AccountDAO;
import com.aurionpro.model.Account;
import com.aurionpro.model.UnApprovedAccount;

public class AccountService {
    
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    // Customer requests new account (in Pending state)
    public boolean createAccountRequest(int userId, String accountType) {
        return accountDAO.insertAccountRequest(userId, accountType);
    }

 // Approve account (ACTIVE, not APPROVED)
    public boolean approveAccount(int accountId, int adminId) {
        long accountNumber = generateAccountNumber();
        return accountDAO.updateAccountStatus(accountId, "ACTIVE", String.valueOf(accountNumber), adminId);
    }

    // Reject account
    public boolean rejectAccount(int accountId, int adminId) {
        return accountDAO.updateAccountStatus(accountId, "REJECTED", null, adminId);
    }

    // Active accounts for user
    public List<Account> getActiveAccountsByUserId(int userId) {
        return accountDAO.getAccountsByUserIdAndStatus(userId, "ACTIVE");
    }
public Account getAccountByAccountId(int accountId){
	return accountDAO.getAccountsByAccountId(accountId);
}


    // Customer dashboard: all accounts
    public List<Account> getAccountsByUserId(int userId) {
        return accountDAO.getAccountsByUserId(userId);
    }
    
    public List<UnApprovedAccount> getAllUnApprovedAccounts(){
    	return accountDAO.getAllUnApprovedAccounts();
    }

 

    // Admin dashboard: pending accounts
    public List<UnApprovedAccount> getPendingAccounts() {
        return accountDAO.getAllUnApprovedAccounts();
    }

    // Utility method to generate random 12-digit account number
    private long generateAccountNumber() {
        return (long)(Math.random() * 1000000000000L);
    }
}
