package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.Beneficiary;
import com.aurionpro.utils.DbConnection;

public class BeneficiaryDAO {
    private Connection connection;

    public BeneficiaryDAO() {
        connection = DbConnection.getInstance().getConnection();
    }

    public boolean addBeneficiary(int userId, String accountNumber, String name) {
        String sql = "INSERT INTO beneficiaries (user_id, beneficiary_account_number, beneficiary_name) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, accountNumber);
            stmt.setString(3, name);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeBeneficiary(int beneficiaryId, int userId) {
        String sql = "DELETE FROM beneficiaries WHERE beneficiary_id = ? AND user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, beneficiaryId);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Beneficiary> getBeneficiariesByUserId(int userId) {
        List<Beneficiary> list = new ArrayList<>();
        String sql = "SELECT * FROM beneficiaries WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Beneficiary b = new Beneficiary();
                    b.setBeneficiaryId(rs.getInt("beneficiary_id"));
                    b.setUserId(rs.getInt("user_id"));
                    b.setBeneficiaryAccountNumber(rs.getString("beneficiary_account_number"));
                    b.setBeneficiaryName(rs.getString("beneficiary_name"));
                    b.setAddedAt(rs.getTimestamp("added_at"));
                    list.add(b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
