package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {

	public static Connection getMySQLConnection() {
		//hostname neu dung local, Public ID 
		String hostName = "localhost";
		String dbName = "automationfc";
		String userName = "root";
		String password = "root";
		return getMySQLConnection(hostName, dbName, userName, password);
	}
	
	private static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) {
		Connection conn = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver"); using for version older jdk11
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			
			String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
