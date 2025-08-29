package com.aurionpro.service;



import java.util.List;

import com.aurionpro.dao.AccountDAO;
import com.aurionpro.dao.BeneficiaryDAO;
import com.aurionpro.model.Beneficiary;

public class BeneficiaryService {
    
    private BeneficiaryDAO beneficiaryDAO;
    private AccountDAO accountDAO;

    public BeneficiaryService() {
        beneficiaryDAO = new BeneficiaryDAO();
        accountDAO = new AccountDAO();
    }

 // Add beneficiary with name
    public boolean addBeneficiary(int userId, String accountNumber, String beneficiaryName) {
        if (!accountDAO.checkAccountExists(accountNumber)) return false;
        return beneficiaryDAO.addBeneficiary(userId, accountNumber, beneficiaryName);
    }



    // Remove beneficiary
    public boolean removeBeneficiary(int beneficiaryId, int userId) {
        return beneficiaryDAO.removeBeneficiary(beneficiaryId, userId);
    }

    // Get all beneficiaries for a customer
    public List<Beneficiary> getBeneficiariesByUserId(int userId) {
        return beneficiaryDAO.getBeneficiariesByUserId(userId);
    }
}
