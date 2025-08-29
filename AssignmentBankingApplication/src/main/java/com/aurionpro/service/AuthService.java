package com.aurionpro.service;

import com.aurionpro.dao.UserDAO;
import com.aurionpro.model.User;

public class AuthService {
    
    private UserDAO userDAO;

    public AuthService() {
        userDAO = new UserDAO();
    }

    public User authenticateUser(String username, String password) {
        // Business logic could include hashing check
    //	System.out.println(username+"service");
    //	System.out.println(password+" service");
        return userDAO.getUserByCredentials(username, password);
    }
}
