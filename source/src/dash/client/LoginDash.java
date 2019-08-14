package dash.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

import dash.client.rpc.AppService;
import dash.client.rpc.AppServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LoginDash implements EntryPoint {

	public void onModuleLoad() {

		AppServiceAsync rpcService = GWT.create(AppService.class);
		HandlerManager eventBus = new HandlerManager(null);
		AppController appViewer = new AppController(eventBus, rpcService);
		appViewer.go(RootPanel.get());
	}

}
