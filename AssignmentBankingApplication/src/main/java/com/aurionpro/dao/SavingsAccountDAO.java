package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.aurionpro.utils.DbConnection;

public class SavingsAccountDAO {
    private Connection connection;

    public SavingsAccountDAO() {
        connection = DbConnection.getInstance().getConnection();
    }

    public boolean createSavingsAccount(int accountId) {
        String sql = "INSERT INTO savings_accounts (account_id, balance) VALUES (?, 0.00)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public double getBalance(int accountId) {
        String sql = "SELECT balance FROM savings_accounts WHERE account_id = ?";
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
        String sql = "UPDATE savings_accounts SET balance = ? WHERE account_id = ?";
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
