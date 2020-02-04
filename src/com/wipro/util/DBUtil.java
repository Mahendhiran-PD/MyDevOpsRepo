package com.wipro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static Connection con;

	public static Connection getConnection() {
		try {
			if (con == null) {

				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return con;
	}
}
