package dash.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.sencha.gxt.widget.core.client.box.MessageBox;

import dash.client.event.DashEvent;
import dash.client.event.DashEventHandler;
import dash.client.event.FormItemEvent;
import dash.client.event.FormItemEventHandler;
import dash.client.event.LoginEvent;
import dash.client.event.LoginEventHandler;
import dash.client.event.LogoffEvent;
import dash.client.event.LogoffEventHandler;
import dash.client.presenter.DashPresenter;
import dash.client.presenter.FormItemPresenter;
import dash.client.presenter.LoginPresenter;
import dash.client.presenter.Presenter;
import dash.client.rpc.AppServiceAsync;
import dash.client.view.DashView;
import dash.client.view.FormItemView;
import dash.client.view.LoginView;
import dash.shared.model.Item;
import dash.shared.model.User;

public class AppController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final AppServiceAsync rpcService;
	private HasWidgets container;
	private String message;

	private User user;

	public AppController(HandlerManager eventBus, AppServiceAsync rpcService) {
		super();
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();

		user = new User();
		message = null;
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {

			@Override
			public void Login(LoginEvent event) {
				message = null;
				doLogin();
			}

		});

		eventBus.addHandler(DashEvent.TYPE, new DashEventHandler() {

			@Override
			public void onDash(DashEvent event) {
				if (user.getId() != -1) {
					message = null;
					doDash();
				} else {
					naoLogado();
					doLogin();
				}

			}

		});

		eventBus.addHandler(FormItemEvent.TYPE, new FormItemEventHandler() {

			@Override
			public void addItem(FormItemEvent event) {
				if (user.getId() != -1) {
					doFormItem(event.getItem());
				} else {
					naoLogado();
					doLogin();
				}

			}

		});

		eventBus.addHandler(LogoffEvent.TYPE, new LogoffEventHandler() {

			@Override
			public void logoff(LogoffEvent event) {
				user.setId(-1);
				doLogin();
			}
		});

	}

	protected void naoLogado() {
		message = new String("Não Logado!");
	}

	private void doLogin() {
		History.newItem("login");
	}

	private void doDash() {
		History.newItem("dash");
	}

	private void doFormItem(Item item) {

		if (item == null) {
			History.newItem("formitem");
		} else {
			History.newItem("formitem", false);
			Presenter presenter = new FormItemPresenter(rpcService, eventBus, new FormItemView(item), item);
			presenter.go(container);
		}

	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("login");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			switch (token) {
			case "login":
				presenter = new LoginPresenter(rpcService, eventBus, new LoginView(message), user);
				break;
			case "dash":
				if (user.getId() != -1) {
					message = null;
					goDash();
				} else {
					naoLogado();
					presenter = new LoginPresenter(rpcService, eventBus, new LoginView(message), user);
				}

				break;
			case "formitem":
				if (user.getId() != -1) {
					presenter = new FormItemPresenter(rpcService, eventBus, new FormItemView(null), null);
				} else {
					naoLogado();
					presenter = new LoginPresenter(rpcService, eventBus, new LoginView(message), user);
				}

				break;

			default:
				break;
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}

	private void goDash() {

		rpcService.listItens(new AsyncCallback<List<Item>>() {

			@Override
			public void onSuccess(List<Item> result) {

				Presenter presenter = new DashPresenter(rpcService, eventBus, new DashView(rpcService, result));
				presenter.go(container);
			}

			@Override
			public void onFailure(Throwable caught) {

				Presenter presenter = new DashPresenter(rpcService, eventBus,
						new DashView(rpcService, new ArrayList<Item>()));
				presenter.go(container);

				MessageBox messageBox = new MessageBox("Não foi possivel carregar os dados");
				messageBox.show();
			}
		});

	}

}
