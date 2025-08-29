package com.aurionpro.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Account;
import com.aurionpro.model.Beneficiary;
import com.aurionpro.model.User;
import com.aurionpro.service.AccountService;
import com.aurionpro.service.BeneficiaryService;
import com.aurionpro.service.TransactionService;

@WebServlet("/transfer-money")
public class TransferMoneyServlet extends HttpServlet {
    
    private TransactionService transactionService;
    private AccountService accountService;
    
    public TransferMoneyServlet() {
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

            List<Beneficiary> beneficiaries = new BeneficiaryService().getBeneficiariesByUserId(user.getUserId());
            request.setAttribute("beneficiaries", beneficiaries);

            request.getRequestDispatcher("transfer-money.jsp").forward(request, response);

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
            int fromAccountId = Integer.parseInt(request.getParameter("fromAccount"));
            String toAccountNumber = request.getParameter("toAccountNumber");
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));
            String description = request.getParameter("description");
            
            boolean success = transactionService.transferMoney(fromAccountId, toAccountNumber, amount, description);
            
            if (success) {
                request.setAttribute("success", "Money transferred successfully!");
            } else {
                request.setAttribute("error", "Transfer failed. Please check account details and balance.");
            }
            
            List<Account> accounts = accountService.getActiveAccountsByUserId(user.getUserId());
            request.setAttribute("accounts", accounts);
            request.getRequestDispatcher("transfer-money.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while processing your request");
            response.sendRedirect("customer-dashboard");
        }
    }
}