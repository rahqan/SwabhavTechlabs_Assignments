package com.aurionpro.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	private static DbConnection instance;
	private Connection connection;
	
	private DbConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app", "root", "Rahqan@123");
			System.out.println("Database connection established.");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: " + e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DbConnection getInstance() {

		if (instance == null) {
			instance = new DbConnection();
		}

		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}
