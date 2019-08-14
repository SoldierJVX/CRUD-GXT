package dash.client.view;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent.ParseErrorHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.IntegerField;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

import dash.client.presenter.FormItemPresenter;
import dash.shared.model.Item;

public class FormItemView extends Composite implements FormItemPresenter.Display {

	private final int WIDTH_BOX = 350;
	private final int HEIGTH_BOX = 275;

	private final int HEIGHT_SCREEN = Window.getClientHeight();
	private final int WIDTH_SCREEN = Window.getClientWidth();

	private ContentPanel root;

	private TextArea txtDescription;
	private IntegerField txtAmount;
	private TextField txtDateCreated;
	private TextField txtDateUpdated;

	private TextButton btnSave;
	private TextButton btnClear;
	private TextButton btnDelete;
	private TextButton btnBack;

	public FormItemView(Item item) {

		root = new ContentPanel();

		root.setHeading("Novo Item");
		root.setBounds(calcularCentroX(), calcularCentroY(), WIDTH_BOX, HEIGTH_BOX);
		initWidget(root);

		txtDescription = new TextArea();
		txtDescription.setAllowBlank(false);

		txtAmount = new IntegerField();
		txtAmount.setAllowBlank(false);
		txtAmount.addParseErrorHandler(new ParseErrorHandler() {
			@Override
			public void onParseError(ParseErrorEvent event) {
				MessageBox msg = new MessageBox("Parse Error",
						event.getErrorValue() + " could not be parsed as a number");
				msg.show();
			}
		});
		
		txtDateCreated = new TextField();
		txtDateCreated.setEnabled(false);
		
		txtDateUpdated = new TextField();
		txtDateUpdated.setEnabled(false);
		

		VBoxLayoutContainer vlc = new VBoxLayoutContainer(VBoxLayoutAlign.STRETCH);
		vlc.add(new FieldLabel(txtDescription, "Descrição"));
		vlc.add(new FieldLabel(txtAmount, "Quantidade"));
		vlc.add(new FieldLabel(txtDateCreated, "Criado em"));
		vlc.add(new FieldLabel(txtDateUpdated, "Atualizado em"));

		btnSave = new TextButton("Salvar");
		btnClear = new TextButton("Limpar");
		btnDelete = new TextButton("Excluir");
		btnBack = new TextButton("Voltar");
		
		if(item != null) {
			txtDescription.setText(item.getDescription());
			txtAmount.setText(item.getAmount() + "");
			txtDateCreated.setText(item.getDateCreated().toString());
			txtDateUpdated.setText(item.getDateUpdated().toString());
		}

		root.add(vlc, new MarginData(15, 15, 0, 15));
		root.addButton(btnSave);
		root.addButton(btnClear);
		if(item != null) {
			root.addButton(btnDelete);
		}
		
		root.addButton(btnBack);

	}

	@Override
	public void clearFields() {
		txtDescription.setText("");
		txtAmount.setText("");
	}

	private int calcularCentroY() {

		return (HEIGHT_SCREEN - HEIGTH_BOX) / 2;
	}

	private int calcularCentroX() {
		return (WIDTH_SCREEN - WIDTH_BOX) / 2;
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public TextButton getBtnSave() {
		return btnSave;
	}

	@Override
	public TextButton getBtnClear() {
		return btnClear;
	}

	@Override
	public TextArea getTxtDescription() {
		return txtDescription;
	}

	@Override
	public IntegerField getTxtAmount() {
		return txtAmount;
	}

	@Override
	public TextButton getBtnBack() {
		return btnBack;
	}

	@Override
	public TextButton getBtnDelete() {
		return btnDelete;
	}

}
