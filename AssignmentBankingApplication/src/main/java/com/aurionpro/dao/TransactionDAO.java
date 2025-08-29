package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.Transaction;
import com.aurionpro.utils.DbConnection;

public class TransactionDAO {
    private Connection connection;

    public TransactionDAO() {
        connection = DbConnection.getInstance().getConnection();
    }

    public boolean insertTransaction(int fromAccountId, String toAccountNumber, double amount, String type, String description) {
        String sql = "INSERT INTO transactions (from_account_id, to_account_number, transaction_type, amount, description, status) VALUES (?, ?, ?, ?, ?, 'SUCCESS')";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fromAccountId);
            stmt.setString(2, toAccountNumber);
            stmt.setString(3, type);
            stmt.setDouble(4, amount);
            stmt.setString(5, description);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Transaction> getTransactionsByDateRange(int accountId, String fromDate, String toDate) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE from_account_id = ? AND transaction_date BETWEEN ? AND ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            stmt.setString(2, fromDate);
            stmt.setString(3, toDate);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transaction txn = new Transaction();
                    txn.setTransactionId(rs.getInt("transaction_id"));
                    txn.setFromAccountId(rs.getInt("from_account_id"));
                    txn.setToAccountNumber(rs.getString("to_account_number"));
                    txn.setTransactionType(rs.getString("transaction_type"));
                    txn.setAmount(rs.getDouble("amount"));
                    txn.setDescription(rs.getString("description"));
                    txn.setTransactionDate(rs.getTimestamp("transaction_date"));
                    txn.setStatus(rs.getString("status"));
                    list.add(txn);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
