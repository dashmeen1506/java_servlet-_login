package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class DBUtil {
	
	static Connection conn=null;
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "harpreet01");
			if(!conn.isClosed())
				System.out.print("Connection established");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		// TODO Auto-generated method stub
		return conn;
	}
}