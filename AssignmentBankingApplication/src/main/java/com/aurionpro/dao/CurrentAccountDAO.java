package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.aurionpro.utils.DbConnection;

public class CurrentAccountDAO {
    private Connection connection;

    public CurrentAccountDAO() {
        connection = DbConnection.getInstance().getConnection();
    }

    public boolean createCurrentAccount(int accountId, double cashCreditAllowed) {
        String sql = "INSERT INTO current_accounts (account_id, balance, cash_credit_allowed, cash_credit_remaining) VALUES (?, 0.00, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            stmt.setDouble(2, cashCreditAllowed);
            stmt.setDouble(3, cashCreditAllowed);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public double getBalance(int accountId) {
        String sql = "SELECT balance FROM current_accounts WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getDouble("balance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public boolean updateBalance(int accountId, double newBalance) {
        String sql = "UPDATE current_accounts SET balance = ? WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, newBalance);
            stmt.setInt(2, accountId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
