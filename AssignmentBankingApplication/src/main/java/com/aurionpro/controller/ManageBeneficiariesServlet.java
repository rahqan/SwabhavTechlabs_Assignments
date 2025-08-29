package com.aurionpro.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Beneficiary;
import com.aurionpro.model.User;
import com.aurionpro.service.BeneficiaryService;


@WebServlet("/manage-beneficiaries")
public class ManageBeneficiariesServlet extends HttpServlet {
    
    private BeneficiaryService beneficiaryService;
    
    public ManageBeneficiariesServlet() {
        beneficiaryService = new BeneficiaryService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || !"CUSTOMER".equals(user.getUserType())) {
            response.sendRedirect("login");
            return;
        }
        
        try {
            List<Beneficiary> beneficiaries = beneficiaryService.getBeneficiariesByUserId(user.getUserId());
            request.setAttribute("beneficiaries", beneficiaries);
            request.getRequestDispatcher("manage-beneficiaries.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("customer-dashboard");
        }
    }
}