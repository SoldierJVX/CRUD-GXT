package dash.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class DashEvent extends GwtEvent<DashEventHandler> {
	public static Type<DashEventHandler> TYPE = new Type<DashEventHandler>();

	@Override
	public Type<DashEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DashEventHandler handler) {
		handler.onDash(this);
	}

}
