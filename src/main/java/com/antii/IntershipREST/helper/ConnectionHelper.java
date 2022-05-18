package com.antii.IntershipREST.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

	private Connection con ;
	
	public ConnectionHelper() throws ClassNotFoundException, SQLException  {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		try {
			 con = DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/severin_bumbaru","admin","admin");
		} catch (SQLException sqle) {
			throw new SQLException(sqle);
		}  
	}
	
	public Connection getConnection() {
		return con;
	}
 
}
