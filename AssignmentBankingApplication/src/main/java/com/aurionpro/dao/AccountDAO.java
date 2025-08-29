package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.Account;
import com.aurionpro.model.UnApprovedAccount;
import com.aurionpro.utils.DbConnection;

public class AccountDAO {
    private Connection connection;

    public AccountDAO() {
        connection = DbConnection.getInstance().getConnection();
    }

    public boolean insertAccountRequest(int userId, String accountType) {
    	System.out.println(userId);
    	System.out.println(accountType);
        String sql = "INSERT INTO accounts (user_id, account_type, status) VALUES (?, ?, 'PENDING')";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, accountType);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
 // Check if account exists by account number
    public boolean checkAccountExists(String accountNumber) {
        String sql = "SELECT 1 FROM accounts WHERE account_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getAccountIdByNumber(String accountNumber) {
        String sql = "SELECT account_id FROM accounts WHERE account_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("account_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public List<Account> getAccountsByUserId(int userId) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT a.account_id, a.user_id, a.account_number, a.account_type, a.status, " +
                     "a.created_at, a.approved_at, a.approved_by, " +
                     "s.balance AS savings_balance, " +
                     "c.balance AS current_balance, " +
                     "c.cash_credit_allowed, c.cash_credit_remaining " +
                     "FROM accounts a " +
                     "LEFT JOIN savings_accounts s ON a.account_id = s.account_id " +
                     "LEFT JOIN current_accounts c ON a.account_id = c.account_id " +
                     "WHERE a.user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Account acc = new Account();
                    acc.setAccountId(rs.getInt("account_id"));
                    acc.setUserId(rs.getInt("user_id"));
                    acc.setAccountNumber(rs.getString("account_number"));
                    acc.setAccountType(rs.getString("account_type"));
                    acc.setStatus(rs.getString("status"));
                    acc.setCreatedAt(rs.getTimestamp("created_at"));
                    acc.setApprovedAt(rs.getTimestamp("approved_at"));
                    acc.setApprovedBy(rs.getInt("approved_by"));

                    // Set balances depending on account type
                    if ("SAVINGS".equalsIgnoreCase(acc.getAccountType())) {
                        acc.setBalance(rs.getDouble("savings_balance"));
                    } else if ("CURRENT".equalsIgnoreCase(acc.getAccountType())) {
                        acc.setBalance(rs.getDouble("current_balance"));
                        acc.setCashCreditAllowed(rs.getDouble("cash_credit_allowed"));
                        acc.setCashCreditRemaining(rs.getDouble("cash_credit_remaining"));
                    }

                    list.add(acc);
                    System.out.println(acc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



 // Get accounts by userId + status (with balances from savings/current tables)
    public List<Account> getAccountsByUserIdAndStatus(int userId, String status) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT a.account_id, a.user_id, a.account_number, a.account_type, a.status, " +
                     "a.created_at, a.approved_at, a.approved_by, " +
                     "s.balance AS savings_balance, " +
                     "c.balance AS current_balance, " +
                     "c.cash_credit_allowed, c.cash_credit_remaining " +
                     "FROM accounts a " +
                     "LEFT JOIN savings_accounts s ON a.account_id = s.account_id " +
                     "LEFT JOIN current_accounts c ON a.account_id = c.account_id " +
                     "WHERE a.user_id = ? AND a.status = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Account acc = new Account();
                    acc.setAccountId(rs.getInt("account_id"));
                    acc.setUserId(rs.getInt("user_id"));
                    acc.setAccountNumber(rs.getString("account_number"));
                    acc.setAccountType(rs.getString("account_type"));
                    acc.setStatus(rs.getString("status"));
                    acc.setCreatedAt(rs.getTimestamp("created_at"));
                    acc.setApprovedAt(rs.getTimestamp("approved_at"));
                    acc.setApprovedBy(rs.getInt("approved_by"));

                    if ("SAVINGS".equalsIgnoreCase(acc.getAccountType())) {
                        acc.setBalance(rs.getDouble("savings_balance"));
                    } else if ("CURRENT".equalsIgnoreCase(acc.getAccountType())) {
                        acc.setBalance(rs.getDouble("current_balance"));
                        acc.setCashCreditAllowed(rs.getDouble("cash_credit_allowed"));
                        acc.setCashCreditRemaining(rs.getDouble("cash_credit_remaining"));
                    }

                    list.add(acc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Account getAccountsByAccountId(int accountId) {
        Account acc = null;
        String sql = "SELECT a.account_id, a.user_id, a.account_number, a.account_type, a.status, " +
                     "a.created_at, a.approved_at, a.approved_by, " +
                     "s.balance AS savings_balance, " +
                     "c.balance AS current_balance, " +
                     "c.cash_credit_allowed, c.cash_credit_remaining " +
                     "FROM accounts a " +
                     "LEFT JOIN savings_accounts s ON a.account_id = s.account_id " +
                     "LEFT JOIN current_accounts c ON a.account_id = c.account_id " +
                     "WHERE a.account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    acc = new Account();
                    acc.setAccountId(rs.getInt("account_id"));
                    acc.setUserId(rs.getInt("user_id"));
                    acc.setAccountNumber(rs.getString("account_number"));
                    acc.setAccountType(rs.getString("account_type"));
                    acc.setStatus(rs.getString("status"));
                    acc.setCreatedAt(rs.getTimestamp("created_at"));
                    acc.setApprovedAt(rs.getTimestamp("approved_at"));
                    acc.setApprovedBy(rs.getInt("approved_by"));

                    if ("SAVINGS".equalsIgnoreCase(acc.getAccountType())) {
                        acc.setBalance(rs.getDouble("savings_balance"));
                    } else if ("CURRENT".equalsIgnoreCase(acc.getAccountType())) {
                        acc.setBalance(rs.getDouble("current_balance"));
                        acc.setCashCreditAllowed(rs.getDouble("cash_credit_allowed"));
                        acc.setCashCreditRemaining(rs.getDouble("cash_credit_remaining"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }



    public boolean updateAccountStatus(int accountId, String status, String accountNumber, Integer adminId) {
        String sql = "UPDATE accounts SET status = ?, account_number = ?, approved_at = NOW(), approved_by = ? WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, accountNumber);
            if (adminId != null) stmt.setInt(3, adminId);
            else stmt.setNull(3, Types.INTEGER);
            stmt.setInt(4, accountId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    public List<Account> getAccountsByStatus(String status) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE status = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapToAccount(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<UnApprovedAccount> getAllUnApprovedAccounts() {
        List<UnApprovedAccount> unapprovedAccounts = new ArrayList<>();

        String sql = "SELECT a.account_id, a.user_id, a.account_number, a.account_type, " +
                     "a.status, a.created_at, u.full_name " +
                     "FROM accounts a " +
                     "JOIN users u ON a.user_id = u.user_id " +
                     "WHERE a.status = 'PENDING'";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UnApprovedAccount acc = new UnApprovedAccount();
                acc.setAccountId(rs.getInt("account_id"));
                acc.setUserId(rs.getInt("user_id"));
                acc.setAccountNumber(rs.getString("account_number"));
                acc.setAccountType(rs.getString("account_type"));
                acc.setStatus(rs.getString("status"));
                acc.setCreatedAt(rs.getTimestamp("created_at"));
                acc.setCustomerName(rs.getString("full_name"));

                unapprovedAccounts.add(acc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return unapprovedAccounts;
    }



    private Account mapToAccount(ResultSet rs) throws SQLException {
        Account acc = new Account();
        acc.setAccountId(rs.getInt("account_id"));
        acc.setUserId(rs.getInt("user_id"));
        acc.setAccountNumber(rs.getString("account_number"));
        acc.setAccountType(rs.getString("account_type"));
        acc.setStatus(rs.getString("status"));
        acc.setCreatedAt(rs.getTimestamp("created_at"));
        acc.setApprovedAt(rs.getTimestamp("approved_at"));
        acc.setApprovedBy(rs.getInt("approved_by"));
        return acc;
    }
    
    
}
