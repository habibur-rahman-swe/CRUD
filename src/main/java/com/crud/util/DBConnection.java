package com.crud.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	// database properties
	private static final String URL = "jdbc:mysql://localhost:3306/employeedirectory";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static Connection connection;
	
	// define the static method
	public static Connection openConnection() {
		if (connection != null) return connection;
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
