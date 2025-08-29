package com.aurionpro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.User;
import com.aurionpro.model.Account;
import com.aurionpro.service.AuthService;
import com.aurionpro.service.AccountService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    private AuthService authService;
    private AccountService accountService;
    
    public LoginServlet() {
        authService = new AuthService();
        accountService = new AccountService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            User user = authService.authenticateUser(username, password);
          
            if (user != null) {
                System.out.println("Logged in: " + user.getFullName());
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("userType", user.getUserType());
                
                if ("ADMIN".equals(user.getUserType())) {
                    response.sendRedirect("admin-dashboard");
                } else if ("CUSTOMER".equals(user.getUserType())) {
                    // fetch accounts and add to session
                    List<Account> accounts = accountService.getAccountsByUserId(user.getUserId());
                    for(Object account:accounts) {
                    	System.out.println(account);
                    }
                    session.setAttribute("accounts", accounts);

                    response.sendRedirect("customer-dashboard");
                } else {
                    request.setAttribute("error", "Unauthorized user type");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                System.out.println("Authentication failed, NULL returned");
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred during login");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
