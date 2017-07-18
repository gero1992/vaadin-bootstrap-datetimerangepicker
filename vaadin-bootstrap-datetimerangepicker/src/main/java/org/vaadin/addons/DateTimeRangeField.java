package org.vaadin.addons;

import java.util.Date;

import org.vaadin.addons.client.DateTimeRangeFieldServerRpc;
import org.vaadin.addons.client.DateTimeRangeFieldState;
import org.vaadin.addons.type.DateTimeRange;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractField;

// This is the server-side UI component that provides public API 
// for MyComponent
@JavaScript({ "js/jquery.min.js", "js/moment.min.js", "js/daterangepicker.js" })
public class DateTimeRangeField extends AbstractField<DateTimeRange> {

	// To process events from the client, we implement ServerRpc
	private DateTimeRangeFieldServerRpc rpc = new DateTimeRangeFieldServerRpc() {

		// Event received from client - user clicked our widget
		@Override
		public void valueChanged(Date from, Date to) {
			setValue(new DateTimeRange(from, to));
		}
	};

	public DateTimeRangeField() {
		// To receive events from the client, we register ServerRpc
		registerRpc(this.rpc);
	}

	// We must override getState() to cast the state to MyComponentState
	@Override
	protected DateTimeRangeFieldState getState() {
		return (DateTimeRangeFieldState) super.getState();
	}

	@Override
	public Class<? extends DateTimeRange> getType() {
		return DateTimeRange.class;
	}
}
