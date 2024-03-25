package connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	
	static String DRIVER = "com.mysql.cj.jdbc.Driver";
	static String CONNECTION_URL = "jdbc:mysql://localhost:3306/acc";
	static String USERNAME = "root";
	static String PASSWORD ="admin";
	
	public static Connection getConnection() {
		Connection con=null;
		try {
		Class.forName(DRIVER);
		con=DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);
		}
		catch(Exception e) {
			System.out.print(e);
		}
		return con;
	}
	
}
