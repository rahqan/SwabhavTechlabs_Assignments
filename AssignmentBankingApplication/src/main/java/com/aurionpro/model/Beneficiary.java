package com.aurionpro.model;



import java.sql.Timestamp;

public class Beneficiary {
    private int beneficiaryId;
    private int userId;
    private String beneficiaryAccountNumber;
    private String beneficiaryName;
    private Timestamp addedAt;

    public Beneficiary() {}

    public Beneficiary(int beneficiaryId, int userId, String beneficiaryAccountNumber,
                       String beneficiaryName, Timestamp addedAt) {
        this.beneficiaryId = beneficiaryId;
        this.userId = userId;
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
        this.beneficiaryName = beneficiaryName;
        this.addedAt = addedAt;
    }

    // Getters & Setters
    public int getBeneficiaryId() { return beneficiaryId; }
    public void setBeneficiaryId(int beneficiaryId) { this.beneficiaryId = beneficiaryId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getBeneficiaryAccountNumber() { return beneficiaryAccountNumber; }
    public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) { this.beneficiaryAccountNumber = beneficiaryAccountNumber; }
    public String getBeneficiaryName() { return beneficiaryName; }
    public void setBeneficiaryName(String beneficiaryName) { this.beneficiaryName = beneficiaryName; }
    public Timestamp getAddedAt() { return addedAt; }
    public void setAddedAt(Timestamp addedAt) { this.addedAt = addedAt; }

    @Override
    public String toString() {
        return "Beneficiary{" + "beneficiaryId=" + beneficiaryId + ", userId=" + userId +
                ", beneficiaryAccountNumber='" + beneficiaryAccountNumber + '\'' +
                ", beneficiaryName='" + beneficiaryName + '\'' + '}';
    }
}
