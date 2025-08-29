package com.aurionpro.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.UnApprovedAccount;
import com.aurionpro.model.User;
import com.aurionpro.service.AccountService;

@WebServlet("/admin-dashboard")
public class AdminDashboardServlet extends HttpServlet {
    
    private AccountService accountService;
    
    public AdminDashboardServlet() {
        accountService = new AccountService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || !"ADMIN".equals(user.getUserType())) {
            response.sendRedirect("login");
            return;
        }
        
        try {
            List<UnApprovedAccount> pendingAccounts = accountService.getPendingAccounts();
            
            request.setAttribute("pendingAccounts", pendingAccounts);
            request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading dashboard");
            request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
        }
    }
}