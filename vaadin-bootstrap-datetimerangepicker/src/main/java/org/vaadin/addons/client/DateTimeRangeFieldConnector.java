package org.vaadin.addons.client;

import java.util.Date;

import org.vaadin.addons.DateTimeRangeField;
import org.vaadin.addons.client.VDateTimeRangeField.DateRangeFieldClientUpdateValueHandler;

import com.google.gwt.core.client.JsDate;
import com.google.gwt.user.client.Window;
import com.vaadin.client.VConsole;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(DateTimeRangeField.class)
public class DateTimeRangeFieldConnector extends AbstractComponentConnector {

	// ServerRpc is used to send events to server. Communication implementation
	// is automatically created here
	DateTimeRangeFieldServerRpc rpc = RpcProxy.create(DateTimeRangeFieldServerRpc.class, this);

	public DateTimeRangeFieldConnector() {

		// To receive RPC events from server, we register ClientRpc
		// implementation
		registerRpc(DateTimeRangeFieldClientRpc.class, new DateTimeRangeFieldClientRpc() {
			public void alert(String message) {
				Window.alert(message);
			}
		});

		getWidget().setUpdateValueHandler(new DateRangeFieldClientUpdateValueHandler() {

			@Override
			public void onUpdateValue(JsDate start, JsDate end) {
				VConsole.error("abc");
				VConsole.error(start.toString());
				Date startDate = new Date((long) start.getTime());
				Date endDate = new Date((long) end.getTime());
				VConsole.error("def");
				VConsole.error(startDate.toString());
				DateTimeRangeFieldConnector.this.rpc.valueChanged(startDate, endDate);
			}
		});

	}

	// We must implement getWidget() to cast to correct type
	// (this will automatically create the correct widget type)
	@Override
	public VDateTimeRangeField getWidget() {
		return (VDateTimeRangeField) super.getWidget();
	}

	// We must implement getState() to cast to correct type
	@Override
	public DateTimeRangeFieldState getState() {
		return (DateTimeRangeFieldState) super.getState();
	}

	// Whenever the state changes in the server-side, this method is called
	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		// State is directly readable in the client after it is set in server
		// final String text = getState().text;
		// getWidget().setText(text);
	}
}
