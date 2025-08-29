package com.aurionpro.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Account;
import com.aurionpro.model.User;
import com.aurionpro.service.AccountService;



@WebServlet("/customer-dashboard")
public class CustomerDashboardServlet extends HttpServlet {
    
    private AccountService accountService;
    
    public CustomerDashboardServlet() {
        accountService = new AccountService();
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
            List<Account> accounts = accountService.getAccountsByUserId(user.getUserId());
            request.setAttribute("accounts", accounts);
            request.getRequestDispatcher("customer-dashboard.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading dashboard");
            request.getRequestDispatcher("customer-dashboard.jsp").forward(request, response);
        }
    }
}