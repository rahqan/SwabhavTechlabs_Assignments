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
import com.aurionpro.model.Transaction;
import com.aurionpro.model.User;
import com.aurionpro.service.AccountService;
import com.aurionpro.service.TransactionService;




@WebServlet("/statement")
public class StatementServlet extends HttpServlet {
    
    private TransactionService transactionService;
    private AccountService accountService;
    
    public StatementServlet() {
        transactionService = new TransactionService();
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
            List<Account> accounts = accountService.getActiveAccountsByUserId(user.getUserId());
            request.setAttribute("accounts", accounts);
            request.getRequestDispatcher("statement.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("customer-dashboard");
        }
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
            int accountId = Integer.parseInt(request.getParameter("accountId"));
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            
            List<Transaction> transactions = transactionService.getTransactionStatement(accountId, fromDate, toDate);
            
            request.setAttribute("transactions", transactions);
            request.setAttribute("fromDate", fromDate);
            request.setAttribute("toDate", toDate);
            request.setAttribute("selectedAccountId", accountId);
            
            List<Account> accounts = accountService.getActiveAccountsByUserId(user.getUserId());
            request.setAttribute("accounts", accounts);
            request.getRequestDispatcher("statement.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while generating statement");
            response.sendRedirect("customer-dashboard");
        }
    }
}