package dash.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dash.client.rpc.AppService;
import dash.server.dao.ItemDao;
import dash.server.dao.UserDao;
import dash.shared.model.Item;
import dash.shared.model.User;

public class AppServiceImpl extends RemoteServiceServlet implements AppService {

	@Override
	public User auth(User user) {

		UserDao dao = new UserDao();

		User defaulUser = dao.authUser(user);

		if (defaulUser != null) {
			return defaulUser;
		}
		return null;

	}

	@Override
	public Item addItem(Item item) {

		ItemDao dao = new ItemDao();

		Item insertOk = dao.addItem(item);

		if (insertOk != null) {
			return item;
		}

		return null;
	}

	public List<Item> listItens() {

		ItemDao dao = new ItemDao();

		return dao.listItens();

	}

	@Override
	public Item editItem(Item item) {
		
		ItemDao dao = new ItemDao();
		
		dao.updateItem(item);
		
		return item;
		
		
	}

	@Override
	public Item deleteItem(Item item) {
		
		ItemDao dao = new ItemDao();
		dao.deleteItem(item);
		
		return null;
		
	}

}
