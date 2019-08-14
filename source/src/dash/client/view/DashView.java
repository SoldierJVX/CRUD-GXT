package dash.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

import dash.client.presenter.DashPresenter;
import dash.client.rpc.AppServiceAsync;
import dash.client.widget.DialogFormItem;
import dash.shared.model.Item;
import dash.shared.model.ItemProperties;

public class DashView extends Composite implements DashPresenter.Display {

	private ContentPanel root;

	private final int HEIGHT_SCREEN = Window.getClientHeight();

	private final AppServiceAsync rpcService;

	private TextButton btnLogoff;
	private TextButton btnAdd;

	private Grid<Item> grid;

	private List<Item> listItens;

	public DashView(AppServiceAsync rpcService, List<Item> result) {

		this.rpcService = rpcService;
		this.listItens = result;

		root = new ContentPanel();
		root.setHeading("Dash - Dashboard");
		root.setHeight(HEIGHT_SCREEN);
		initWidget(root);

		// Create a Dock Panel
		DockPanel dock = new DockPanel();
		dock.setStyleName("cw-DockPanel");
		dock.setSpacing(4);
		dock.setHorizontalAlignment(DockPanel.ALIGN_CENTER);

		menuLateral(dock);

		gridContent(dock);

		root.add(dock);

	}

	private void gridContent(DockPanel dock) {

		ItemProperties props = GWT.create(ItemProperties.class);

		ColumnConfig<Item, Integer> idCol = new ColumnConfig<Item, Integer>(props.id(), 50, "ID");
		ColumnConfig<Item, String> descriptionCol = new ColumnConfig<Item, String>(props.description(), 75,
				"Descrição");
		ColumnConfig<Item, Integer> amountCol = new ColumnConfig<Item, Integer>(props.amount(), 75, "Quantidade");
		ColumnConfig<Item, Date> createdCol = new ColumnConfig<Item, Date>(props.dateCreated(), 75, "Criado em");
		ColumnConfig<Item, Date> updatedCol = new ColumnConfig<Item, Date>(props.dateUpdated(), 100, "Modificado em");

		createdCol.setCell(new DateCell(DateTimeFormat.getFormat("dd/MM/yyyy")));
		updatedCol.setCell(new DateCell(DateTimeFormat.getFormat("dd/MM/yyyy")));

		List<ColumnConfig<Item, ?>> columns = new ArrayList<ColumnConfig<Item, ?>>();
		columns.add(idCol);
		columns.add(descriptionCol);
		columns.add(amountCol);
		columns.add(createdCol);
		columns.add(updatedCol);

		ColumnModel<Item> cm = new ColumnModel<Item>(columns);

		ListStore<Item> store = new ListStore<Item>(props.key());
		store.addAll(listItens);

		grid = new Grid<Item>(store, cm);
		grid.setAllowTextSelection(false);
		grid.getView().setAutoFill(true);
		// grid.getView().setAutoExpandColumn(descriptionCol);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);
		grid.setBorders(false);
		grid.setColumnReordering(true);
		grid.setWidth(1000);

		HorizontalPanel main = new HorizontalPanel();
		main.add(grid);

		// Add center
		dock.add(main, DockPanel.CENTER);

		// Return the content
		dock.ensureDebugId("cwDockPanel");

	}

	private void menuLateral(DockPanel dock) {
		// Menu Lateral
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(5);
		vPanel.setWidth("165px");

		// Add some content to the panel
		btnAdd = new TextButton("Adicionar");
		btnAdd.setWidth(140);
		vPanel.add(btnAdd);

		btnLogoff = new TextButton("Sair");
		btnLogoff.setWidth(140);
		vPanel.add(btnLogoff);

		// Return the content
		vPanel.ensureDebugId("cwVerticalPanel");

		// Add Menu Lateral
		dock.add(vPanel, DockPanel.WEST);
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public TextButton getBtnLogoff() {
		return btnLogoff;
	}

	@Override
	public TextButton getBtnAdd() {
		return btnAdd;
	}

	@Override
	public Grid getGrid() {
		return grid;
	}

}
