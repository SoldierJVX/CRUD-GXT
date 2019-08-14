package dash.client.view;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;

import dash.client.presenter.LoginPresenter;

public class LoginView extends Composite implements LoginPresenter.Display, IsWidget {

	private final int WIDTH_BOX = 300;
	private final int HEIGTH_BOX = 200;

	private final int HEIGHT_SCREEN = Window.getClientHeight();
	private final int WIDTH_SCREEN = Window.getClientWidth();

	private ContentPanel root;

	private TextField txtLogin;
	private PasswordField txtPassword;

	private TextButton btnSave;
	private TextButton btnCancel;
	
	

	public LoginView(String message) {

		root = new ContentPanel();

		root.setHeading("Login - Dashboard");
		root.setBounds(calcularCentroX(), calcularCentroY(), WIDTH_BOX, HEIGTH_BOX);
		initWidget(root);

		txtLogin = new TextField();
		txtLogin.setText("asd@asd.com");
		txtLogin.setAllowBlank(false);
		txtLogin.setEmptyText("Digite seu email");
		
		HTML label = new HTML();
		if(message != null) {
			label.setHTML("<h1 style='color:red;'>" + message + "</h1>");
			message = null;
		}

		txtPassword = new PasswordField();
		txtPassword.setText("123");

		VBoxLayoutContainer vlc = new VBoxLayoutContainer(VBoxLayoutAlign.STRETCH);
		vlc.add(new FieldLabel(txtLogin, "Login"));
		vlc.add(new FieldLabel(txtPassword, "Senha"));
		vlc.add(label);

		btnSave = new TextButton("Login");
		btnCancel = new TextButton("Limpar");

		root.add(vlc, new MarginData(15, 15, 0, 15));
		root.addButton(btnSave);
		root.addButton(btnCancel);

	}

	private int calcularCentroY() {

		return (HEIGHT_SCREEN - HEIGTH_BOX) / 2;
	}

	private int calcularCentroX() {
		return (WIDTH_SCREEN - WIDTH_BOX) / 2;
	}

	@Override
	public void clearFields() {
		txtLogin.setText("");
		txtPassword.setText("");
	}

	@Override
	public TextButton getBtnSave() {
		return btnSave;
	}

	@Override
	public TextButton getBtnCancel() {
		return btnCancel;
	}

	@Override
	public PasswordField getTxtPassword() {
		return txtPassword;
	}

	@Override
	public TextField getTxtLogin() {
		return txtLogin;
	}

	public Widget asWidget() {
		return this;
	}

}
