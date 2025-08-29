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

@WebServlet("/approve-account")
public class ApproveAccountServlet extends HttpServlet {
    
    private AccountService accountService;
    
    public ApproveAccountServlet() {
        accountService = new AccountService();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !"ADMIN".equals(user.getUserType())) {
            response.sendRedirect("login");
            return;
        }

        try {
            int accountId = Integer.parseInt(request.getParameter("accountId"));
            String action = request.getParameter("action");

            boolean success = false;
            if ("approve".equals(action)) {
                success = accountService.approveAccount(accountId, user.getUserId()); 
            } else if ("reject".equals(action)) {
                success = accountService.rejectAccount(accountId, user.getUserId());
            }

            if (success) {
                response.sendRedirect("admin-dashboard?success=Account " + action + "d successfully");
            } else {
                response.sendRedirect("admin-dashboard?error=Failed to " + action + " account");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin-dashboard?error=An error occurred");
        }
    }

}