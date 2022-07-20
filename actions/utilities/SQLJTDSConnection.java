package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLJTDSConnection {
	
	public static Connection getSQLServerConnection() {
		String hostName = "localhost";
		String sqlInstanceName = "SQLEXPRESS";
		String database = "automationfc";
		String userName = "sa";
		String password = "root";
		
		return getSQLServerConnection(hostName, sqlInstanceName, database, userName, password);
	}
	
	public static Connection getSQLServerConnection (String hostName, String sqlInstanceName, String database, String userName, String password) {
		Connection conn = null; 
		
		try {
			// Khai bao class Driver cho SQL Server
			// Can thiet for Java 5 only
			//Java 6 tu dong tim kiem ko bat buoc dong nay
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			
			// Cau truc URL Connection danh cho SQL Server
			//vi du 
			// jdbc:jtds:sqlServer://localhost:1433/automationfc;instance=SQLEXPRESS
			String connectionURL = "jdbc:jtds:sqlserver://" + hostName + ":1433//" + database + ";instance=" + sqlInstanceName;
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
