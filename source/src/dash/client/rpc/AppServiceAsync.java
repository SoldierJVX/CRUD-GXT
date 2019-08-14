package dash.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import dash.shared.model.Item;
import dash.shared.model.User;

public interface AppServiceAsync {
	
	void auth(User user, AsyncCallback<User> callback);
	void addItem(Item item, AsyncCallback<Item> callback);
	void editItem(Item item, AsyncCallback<Item> callback);
	void deleteItem(Item item, AsyncCallback<Item> callback);
	void listItens(AsyncCallback<List<Item>> asyncCallback);

}
