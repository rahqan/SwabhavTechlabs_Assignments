package com.aurionpro.service;

import java.math.BigDecimal;
import java.util.List;

import com.aurionpro.dao.AccountDAO;
import com.aurionpro.dao.CurrentAccountDAO;
import com.aurionpro.dao.SavingsAccountDAO;
import com.aurionpro.dao.TransactionDAO;
import com.aurionpro.model.Account;
import com.aurionpro.model.Transaction;

public class TransactionService {
    
    private TransactionDAO transactionDAO;
    private AccountDAO accountDAO;

    public TransactionService() {
        transactionDAO = new TransactionDAO();
        accountDAO = new AccountDAO();
    }
    
    private double getBalance(int accountId, String accountType) {
        if (accountType.equals("SAVINGS")) {
            return new SavingsAccountDAO().getBalance(accountId);
        } else {
            return new CurrentAccountDAO().getBalance(accountId);
        }
    }

    private boolean updateBalance(int accountId, String accountType, double newBalance) {
        if (accountType.equals("SAVINGS")) {
            return new SavingsAccountDAO().updateBalance(accountId, newBalance);
        } else {
            return new CurrentAccountDAO().updateBalance(accountId, newBalance);
        }
    }


    // Transfer money between accounts
    public boolean transferMoney(int fromAccountId, String toAccountNumber, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) return false;

        // Get source account
        Account fromAcc = accountDAO.getAccountsByAccountId(fromAccountId); // or add getAccountById()
        
        double fromBalance = getBalance(fromAccountId, fromAcc.getAccountType());

        if (BigDecimal.valueOf(fromBalance).compareTo(amount) < 0) {
            return false; // insufficient funds
        }

        // Get destination account
        int toAccountId = accountDAO.getAccountIdByNumber(toAccountNumber);
        if (toAccountId == 0) return false;
        Account toAcc = accountDAO.getAccountsByUserId(toAccountId).get(0);

        // Update balances
        boolean debit = updateBalance(fromAccountId, fromAcc.getAccountType(), fromBalance - amount.doubleValue());
        boolean credit = updateBalance(toAccountId, toAcc.getAccountType(), getBalance(toAccountId, toAcc.getAccountType()) + amount.doubleValue());

        // Record transaction
        if (debit && credit) {
            transactionDAO.insertTransaction(fromAccountId, toAccountNumber, amount.doubleValue(), "TRANSFER", description);
            return true;
        }
        return false;
    }


    // Generate statement for date range
    public List<Transaction> getTransactionStatement(int accountId, String fromDate, String toDate) {
        return transactionDAO.getTransactionsByDateRange(accountId, fromDate, toDate);
    }
}

