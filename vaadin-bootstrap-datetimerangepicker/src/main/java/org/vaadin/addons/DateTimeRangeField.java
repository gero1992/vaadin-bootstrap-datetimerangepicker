package org.vaadin.addons;

import java.util.Date;
import java.util.Locale;

import org.vaadin.addons.client.DateTimeRangeFieldServerRpc;
import org.vaadin.addons.client.DateTimeRangeFieldState;
import org.vaadin.addons.type.DateTimeRange;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractField;

// This is the server-side UI component that provides public API
@JavaScript({ "js/jquery.min.js", "js/moment.min.js", "js/daterangepicker.js" })
public class DateTimeRangeField extends AbstractField<DateTimeRange> {

    private static final long serialVersionUID = 1L;

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

    @Override
    public void setLocale(final Locale locale) {
        getState().setLocale(locale);
    }

    public DateTimeRangeField startDate(final Date startDate) {
        getState().setStartDate(startDate);
        return this;
    }

    public DateTimeRangeField endDate(final Date endDate) {
        getState().setEndDate(endDate);
        return this;
    }

    public DateTimeRangeField opens(final String opens) {
        getState().setOpens(opens);
        return this;
    }

    public DateTimeRangeField drops(final String drops) {
        getState().setDrops(drops);
        return this;
    }

    public DateTimeRangeField showDropdowns(final boolean showDropdowns) {
        getState().setShowDropdowns(showDropdowns);
        return this;
    }

    public DateTimeRangeField showWeekNumbers(final boolean showWeekNumbers) {
        getState().setShowWeekNumbers(showWeekNumbers);
        return this;
    }

    public DateTimeRangeField showISOWeekNumbers(final boolean showISOWeekNumbers) {
        getState().setShowISOWeekNumbers(showISOWeekNumbers);
        return this;
    }

    public DateTimeRangeField singleDatePicker(final boolean singleDatePicker) {
        getState().setSingleDatePicker(singleDatePicker);
        return this;
    }

    public DateTimeRangeField timePicker(final boolean timePicker) {
        getState().setTimePicker(timePicker);
        return this;
    }

    public DateTimeRangeField timePicker24Hour(final boolean timePicker24Hour) {
        getState().setTimePicker24Hour(timePicker24Hour);
        return this;
    }

    // in minutes

    public DateTimeRangeField timePickerIncrement(final int timePickerIncrement) {
        getState().setTimePickerIncrement(timePickerIncrement);
        return this;
    }

    //

    public DateTimeRangeField timePickerSeconds(final boolean timePickerSeconds) {
        getState().setTimePickerSeconds(timePickerSeconds);
        return this;
    }

    public DateTimeRangeField dateLimit(final int dateLimit) {
        getState().setDateLimit(dateLimit);
        return this;
    }

    public DateTimeRangeField autoApply(final boolean autoApply) {
        getState().setAutoApply(autoApply);
        return this;
    }

    public DateTimeRangeField linkedCalendars(final boolean linkedCalendars) {
        getState().setLinkedCalendars(linkedCalendars);
        return this;
    }

    public DateTimeRangeField autoUpdateInput(final boolean autoUpdateInput) {
        getState().setAutoUpdateInput(autoUpdateInput);
        return this;
    }

    public DateTimeRangeField refresh() {
        getState().toggleState();
        return this;
    }
}
