package dash.client.presenter;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;

import dash.client.event.DashEvent;
import dash.client.rpc.AppServiceAsync;
import dash.shared.model.User;

public class LoginPresenter implements Presenter {

	private User userAtempt;

	public interface Display {

		TextButton getBtnSave();

		TextButton getBtnCancel();

		PasswordField getTxtPassword();

		TextField getTxtLogin();

		void clearFields();

		Widget asWidget();

	}

	private final AppServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public LoginPresenter(AppServiceAsync rpcService, HandlerManager eventBus, Display display, User user) {
		super();
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		this.userAtempt = user;
	}

	public void bind() {

		display.getBtnSave().addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				authUser();
			}
		});

		display.getBtnCancel().addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				display.clearFields();
			}
		});

		display.getTxtPassword().addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == 13) {
					authUser();
				}
			}
		});

	}

	private void authUser() {

		display.getBtnSave().setEnabled(false);

		userAtempt.setLogin(display.getTxtLogin().getText());
		userAtempt.setPassword(display.getTxtPassword().getText());

		rpcService.auth(userAtempt, new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				MessageBox messageBox = new MessageBox("Algum erro ocorreu!");
				messageBox.show();
				display.getBtnSave().setEnabled(true);
			}

			@Override
			public void onSuccess(User result) {
				if (result != null) {

					userAtempt.setId(result.getId());
					userAtempt.setLogin(result.getLogin());
					userAtempt.setName(result.getName());

					eventBus.fireEvent(new DashEvent());
				} else {
					MessageBox messageBox = new MessageBox("Usuário não encotrado!");
					messageBox.show();
				}

				display.getBtnSave().setEnabled(true);
			}
		});

	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

}
