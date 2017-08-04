package org.vaadin.addons;

import java.util.Locale;

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
    private final DateTimeRangeFieldServerRpc rpc = (from, to) -> setValue(new DateTimeRange(from, to));

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

    public void setShowDropdowns(final boolean showDropdowns) {
        getState().setShowDropdowns(showDropdowns);
    }

    public void setShowWeekNumbers(final boolean showWeekNumbers) {
        getState().setShowWeekNumbers(showWeekNumbers);
    }

    public void setShowISOWeekNumbers(final boolean showISOWeekNumbers) {
        getState().setShowISOWeekNumbers(showISOWeekNumbers);
    }

    public void setSingleDatePicker(final boolean singleDatePicker) {
        getState().setSingleDatePicker(singleDatePicker);
    }

    public void setTimePicker(final boolean timePicker) {
        getState().setTimePicker(timePicker);
    }

    public void setTimePicker24Hour(final boolean timePicker24Hour) {
        getState().setTimePicker24Hour(timePicker24Hour);
    }

    // in minutes

    public void setTimePickerIncrement(final int timePickerIncrement) {
        getState().setTimePickerIncrement(timePickerIncrement);
    }

    //

    public void setTimePickerSeconds(final boolean timePickerSeconds) {
        getState().setTimePickerSeconds(timePickerSeconds);
    }

    public void setDateLimit(final int dateLimit) {
        getState().setDateLimit(dateLimit);
    }

    @Override
    public void setLocale(final Locale locale) {
        getState().setLocale(locale);
    }

    public void setAutoApply(final boolean autoApply) {
        getState().setAutoApply(autoApply);
    }

    public void setLinkedCalendars(final boolean linkedCalendars) {
        getState().setLinkedCalendars(linkedCalendars);
    }

    public void setAutoUpdateInput(final boolean autoUpdateInput) {
        getState().setAutoApply(autoUpdateInput);
    }
}
