package dash.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface DashEventHandler extends EventHandler {
	void onDash(DashEvent event);
}
