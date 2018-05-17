package org.vaadin.addons.datetimerangepicker.client;

import java.util.Date;

import org.vaadin.addons.datetimerangepicker.DateTimeRangeField;

import com.vaadin.client.annotations.OnStateChange;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

// Connector binds client-side widget class to server-side component class
// Connector lives in the client and the @Connect annotation specifies the
// corresponding server-side component
@Connect(DateTimeRangeField.class)
public class DateTimeRangeFieldConnector extends AbstractComponentConnector {

    private static final long serialVersionUID = 1L;

    // ServerRpc is used to send events to server. Communication implementation
    // is automatically created here
    DateTimeRangeFieldServerRpc rpc = RpcProxy.create(DateTimeRangeFieldServerRpc.class, this);

    public DateTimeRangeFieldConnector() {

        getWidget().setUpdateValueHandler((start, end) -> {
            DateTimeRangeFieldConnector.this.rpc.valueChanged(new Date((long) start.getTime()), new Date((long) end.getTime()));
        });

        getWidget().setResetValueHandler(() -> DateTimeRangeFieldConnector.this.rpc.valueReseted());
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
    public void onStateChanged(final StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        getWidget().refresh(getState().getLanguage(), getState().getApplyLabel(), getState().getCancelLabel(), getState().getParentEl(),
                            getState().getStartDate(), getState().getEndDate(), getState().getMinDate(), getState().getMaxDate(), getState().isShowDropdowns(),
                            getState().isShowWeekNumbers(), getState().isShowISOWeekNumbers(), getState().isSingleDatePicker(), getState().isTimePicker(),
                            getState().isTimePicker24Hour(), getState().getTimePickerIncrement(), getState().isTimePickerSeconds(),
                            getState().getDateLimitSpanMoment(), getState().getDateLimitSpanValue(), getState().isAutoApply(), getState().isLinkedCalendars(),
                            getState().isAutoUpdateInput(), getState().getOpens(), getState().getDrops(), getState().getButtonClasses(),
                            getState().getApplyClass(), getState().getCancelClass(), getState().getDateRanges(), getState().isAlwaysShowCalendars(),
                            getState().isShowCustomRangeLabel(), getState().getDatePattern(), getState().isWorkable());
    }

    @OnStateChange("elementUUID")
    void updateElementUUID() {
        getWidget().setElementUUID(getState().getElementUUID());
    }

}