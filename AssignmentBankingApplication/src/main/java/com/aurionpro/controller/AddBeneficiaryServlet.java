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


@WebServlet("/add-beneficiary")
public class AddBeneficiaryServlet extends HttpServlet {
    
    private BeneficiaryService beneficiaryService;
    
    public AddBeneficiaryServlet() {
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
            String accountNumber = request.getParameter("accountNumber");
            String beneficiaryName = request.getParameter("beneficiaryName");

            boolean success = beneficiaryService.addBeneficiary(
                    user.getUserId(), accountNumber, beneficiaryName);

            if (success) {
                // ✅ show success on page
                request.setAttribute("successMessage", "Beneficiary added successfully");
            } else {
                // ✅ show error on page
                request.setAttribute("errorMessage", "Failed to add beneficiary. Account may not exist.");
            }

            // ✅ Forward, not redirect (so attributes stay in request scope)
            request.getRequestDispatcher("manage-beneficiaries.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An unexpected error occurred.");
            request.getRequestDispatcher("manage-beneficiaries.jsp").forward(request, response);
        }
    }


}