package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseContext {
	private final static String dbURL = "jdbc:mysql://localhost:3306/test";
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection(dbURL, "root", "");
			System.out.println("Ket noi thanh cong");
			return conn;
		}
		catch(Exception e) {
			System.out.println("Ket noi khong thanh cong");
		}
		return null;
	}
}
