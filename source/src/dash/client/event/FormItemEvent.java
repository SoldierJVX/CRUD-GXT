package dash.client.event;

import com.google.gwt.event.shared.GwtEvent;

import dash.shared.model.Item;

public class FormItemEvent extends GwtEvent<FormItemEventHandler> {
	public static Type<FormItemEventHandler> TYPE = new Type<FormItemEventHandler>();
	private Item item;

	public FormItemEvent(Item selecionado) {
		this.item = selecionado;
	}

	public Item getItem() {
		return item;
	}

	@Override
	public Type<FormItemEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FormItemEventHandler handler) {
		handler.addItem(this);
	}

}
