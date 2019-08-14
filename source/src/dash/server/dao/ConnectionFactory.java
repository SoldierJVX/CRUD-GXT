package dash.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vitor Mesaque
 */
public final class ConnectionFactory {
	private ConnectionFactory() {
	}

	public static Connection getConnection() throws SQLException {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/login", "root",
					"master");

			return conn;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
	}

	
}