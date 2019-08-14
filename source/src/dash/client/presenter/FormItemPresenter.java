package dash.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.IntegerField;
import com.sencha.gxt.widget.core.client.form.TextArea;

import dash.client.event.DashEvent;
import dash.client.rpc.AppServiceAsync;
import dash.client.view.FormItemView;
import dash.shared.model.Item;

public class FormItemPresenter implements Presenter {

	public interface Display {

		TextButton getBtnSave();

		TextButton getBtnClear();

		TextButton getBtnBack();

		TextButton getBtnDelete();

		TextArea getTxtDescription();

		IntegerField getTxtAmount();

		void clearFields();

		Widget asWidget();

	}

	private final AppServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	private Item item;

	public FormItemPresenter(AppServiceAsync rpcService, HandlerManager eventBus, FormItemView display, Item item) {
		super();
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		this.item = item;
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	private void bind() {
		display.getBtnClear().addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				display.clearFields();
			}
		});

		display.getBtnSave().addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {

				if (display.getTxtAmount().getText() != ""){

					if (item == null) {
						addNewItem();
					} else {
						editItem();
					}

				}else {
					display.getTxtAmount().focus();
					display.getTxtDescription().focus();
					display.getBtnClear().focus();
				}

			}

		});

		display.getBtnBack().addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				eventBus.fireEvent(new DashEvent());
			}
		});

		display.getBtnDelete().addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				deleteItem();
			}
		});
	}

	protected void deleteItem() {
		display.getBtnDelete().setEnabled(false);

		rpcService.deleteItem(item, new AsyncCallback<Item>() {

			@Override
			public void onFailure(Throwable caught) {
				MessageBox messageBox = new MessageBox("Algum erro ocorreu!");
				messageBox.show();
				display.getBtnDelete().setEnabled(true);
			}

			@Override
			public void onSuccess(Item result) {
				eventBus.fireEvent(new DashEvent());
			}
		});

	}

	protected void editItem() {
		display.getBtnSave().setEnabled(false);

		item.setDescription(display.getTxtDescription().getText());
		item.setAmount(display.getTxtAmount().getValue());

		rpcService.editItem(item, new AsyncCallback<Item>() {

			@Override
			public void onFailure(Throwable caught) {
				MessageBox messageBox = new MessageBox("Algum erro ocorreu!");
				messageBox.show();
				display.getBtnSave().setEnabled(true);
			}

			@Override
			public void onSuccess(Item result) {
				if (result != null) {
					eventBus.fireEvent(new DashEvent());
				} else {
					MessageBox messageBox = new MessageBox("Algum erro ocorreu!");
					messageBox.show();
					display.getBtnSave().setEnabled(true);
				}
			}
		});
	}

	private void addNewItem() {

		display.getBtnSave().setEnabled(false);

		Item newItem = new Item();
		newItem.setDescription(display.getTxtDescription().getText());
		newItem.setAmount(display.getTxtAmount().getValue());

		rpcService.addItem(newItem, new AsyncCallback<Item>() {

			@Override
			public void onSuccess(Item result) {
				if (result != null) {
					eventBus.fireEvent(new DashEvent());
				} else {
					MessageBox messageBox = new MessageBox("Algum erro ocorreu!");
					messageBox.show();
					display.getBtnSave().setEnabled(true);
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				MessageBox messageBox = new MessageBox("Algum erro ocorreu!");
				messageBox.show();
				display.getBtnSave().setEnabled(true);
			}
		});

	}

}
