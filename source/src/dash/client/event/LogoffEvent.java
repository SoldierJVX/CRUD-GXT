package dash.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class LogoffEvent extends GwtEvent<LogoffEventHandler>{
	public static Type<LogoffEventHandler> TYPE = new Type<LogoffEventHandler>();

	@Override
	public Type<LogoffEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LogoffEventHandler handler) {
		handler.logoff(this);
	}

}
