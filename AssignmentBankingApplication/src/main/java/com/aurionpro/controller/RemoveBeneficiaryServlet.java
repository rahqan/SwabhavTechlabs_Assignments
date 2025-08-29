package com.aurionpro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.User;
import com.aurionpro.service.BeneficiaryService;

@WebServlet("/remove-beneficiary")
public class RemoveBeneficiaryServlet extends HttpServlet {
    
    private BeneficiaryService beneficiaryService;
    
    public RemoveBeneficiaryServlet() {
        beneficiaryService = new BeneficiaryService();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || !"CUSTOMER".equals(user.getUserType())) {
            response.sendRedirect("login");
            return;
        }
        
        try {
            int beneficiaryId = Integer.parseInt(request.getParameter("beneficiaryId"));
            boolean success = beneficiaryService.removeBeneficiary(beneficiaryId, user.getUserId());
            
            if (success) {
                response.sendRedirect("manage-beneficiaries?success=Beneficiary removed successfully");
            } else {
                response.sendRedirect("manage-beneficiaries?error=Failed to remove beneficiary");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manage-beneficiaries?error=An error occurred");
        }
    }
}