package DB;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static Connection conn;
	static String username = "req-lrg";
	static String password = "f26s94";
	static {
		conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:db01", username, password);
			conn.setAutoCommit(false);
			System.out.println("Connect durchgefuehrt ....");
		} catch (Exception e) {
			System.err.println("Error while connecting to database");
			e.printStackTrace();
			System.exit(1);
		} 
	}
		
	public static Connection getConnection() {
		return conn;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
