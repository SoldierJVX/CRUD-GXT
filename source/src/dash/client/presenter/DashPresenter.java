package dash.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent.RowClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;

import dash.client.event.FormItemEvent;
import dash.client.event.LogoffEvent;
import dash.client.rpc.AppServiceAsync;
import dash.shared.model.Item;

public class DashPresenter implements Presenter {

	public interface Display {

		TextButton getBtnAdd();

		TextButton getBtnLogoff();

		Grid getGrid();

		Widget asWidget();

	}

	private final AppServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public DashPresenter(AppServiceAsync rpcService, HandlerManager eventBus, Display display) {
		super();
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
	}

	private void bind() {

		display.getBtnLogoff().addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				eventBus.fireEvent(new LogoffEvent());
			}
		});

		display.getBtnAdd().addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				eventBus.fireEvent(new FormItemEvent(null));
			}
		});

		display.getGrid().addRowClickHandler(new RowClickHandler() {

			@Override
			public void onRowClick(RowClickEvent event) {
				Item selecionado = (Item) display.getGrid().getSelectionModel().getSelectedItem();
				eventBus.fireEvent(new FormItemEvent(selecionado));
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
