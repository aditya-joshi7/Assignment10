package com.psl.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnectionUtil {
	static Connection cn=null;

	DBConnectionUtil(){
		
	}
	static String driverClass="com.mysql.jdbc.Driver";
	static String url="jdbc:mysql://localhost:3306/contactdb";
	static String username="root";
	static String password="9454197748";
	
	
	public static Connection getConnection(){
		if(cn==null){
			// Optional
			try {
				Class.forName(driverClass);
				cn=DriverManager.getConnection(url,username,password);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
					}
		
		return cn;
		
	
	}
	
	public void closeConnection(){
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

}
