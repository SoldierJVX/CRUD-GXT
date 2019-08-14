package dash.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;

import dash.shared.model.Item;

public class ItemDao {

	private Connection connection;

	public ItemDao() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Item addItem(Item item) {

		try {
			String sql = "INSERT INTO store (description, amount) VALUES ('" + item.getDescription() + "', "
					+ item.getAmount() + ")";

			Statement stmt = this.connection.createStatement();

			stmt.execute(sql);

			return item;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public Item getItem(int id) {

		Item item = null;

		try {
			String sql = "SELECT * FROM store WHERE id = " + id;

			Statement stmt = this.connection.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				item = new Item();

				item.setId(rs.getInt("id"));
				item.setDescription(rs.getString("description"));
				item.setAmount(rs.getInt("amount"));
				item.setDateCreated(rs.getDate("date_created"));
				item.setDateUpdated(rs.getDate("date_updated"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return item;

	}

	public List<Item> listItens() {

		List<Item> itens = new ArrayList<Item>();

		try {
			String sql = "SELECT * FROM store";

			Statement stmt = this.connection.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Item item = new Item();

				item.setId(rs.getInt("id"));
				item.setDescription(rs.getString("description"));
				item.setAmount(rs.getInt("amount"));
				item.setDateCreated(rs.getDate("date_created"));
				item.setDateUpdated(rs.getDate("date_updated"));

				itens.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itens;

	}

	public Item updateItem(Item item) {
		
		try {
			String sql = "UPDATE store SET description = '" + item.getDescription() + "', amount = " + item.getAmount() + ", date_updated = now() WHERE id = " + item.getId();

			Statement stmt = this.connection.createStatement();

			stmt.execute(sql);

			return item;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
		
	}

	public boolean deleteItem(Item item) {

		try {
			String sql = "DELETE FROM store WHERE id = " + item.getId();

			Statement stmt = this.connection.createStatement();

			stmt.execute(sql);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

}
