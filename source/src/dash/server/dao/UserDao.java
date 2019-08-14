package dash.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dash.shared.model.User;

public class UserDao {

	private Connection connection;

	public UserDao() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User authUser(User userAtempt) {

		try {
			String sql = "SELECT * FROM user WHERE login = '" + userAtempt.getLogin() + "' AND password = '"
					+ userAtempt.getPassword() + "'";

			Statement stmt = this.connection.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				User user = new User();
				
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setLogin(rs.getString("login"));

				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
