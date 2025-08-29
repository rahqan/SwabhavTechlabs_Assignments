package com.aurionpro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.User;
import com.aurionpro.service.AccountService;

@WebServlet("/create-account")
public class CreateAccountServlet extends HttpServlet {
    
    private AccountService accountService;
    
    public CreateAccountServlet() {
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
        
        request.getRequestDispatcher("create-account.jsp").forward(request, response);
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
            String accountType = request.getParameter("accountType");
            boolean success = accountService.createAccountRequest(user.getUserId(), accountType);
            
            if (success) {
                request.setAttribute("success", "Account request submitted successfully!");
            } else {
                request.setAttribute("error", "Failed to submit account request");
            }
            
            request.getRequestDispatcher("create-account.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred");
            request.getRequestDispatcher("create-account.jsp").forward(request, response);
        }
    }
}