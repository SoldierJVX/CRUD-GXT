package dash.shared.model;

import java.io.Serializable;

public class User implements Serializable {

	private int id;
	private String name;
	private String login;
	private String password;

	public User() {
		id = -1;
	}

	public User(String name, String login, String password) {
		super();
		this.name = name;
		this.login = login;
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {

		boolean loginEquals = false;
		boolean passEquals = false;

		if (this.login.equals(((User) obj).getLogin())) {
			loginEquals = true;
		}

		if (this.password.equals(((User) obj).getPassword())) {
			passEquals = true;
		}

		if (loginEquals && passEquals) {
			return true;
		}
		return false;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
