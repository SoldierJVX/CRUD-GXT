package dash.client.rpc;


import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import dash.shared.model.Item;
import dash.shared.model.User;

@RemoteServiceRelativePath("auth")
public interface AppService extends RemoteService {
	
	User auth(User user);
	Item addItem(Item item);
	List<Item> listItens();
	Item editItem(Item item);
	Item deleteItem(Item item);

}
