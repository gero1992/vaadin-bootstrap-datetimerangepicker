package org.vaadin.addons;

import java.text.Format;
import java.util.Date;
import java.util.Locale;

import org.vaadin.addons.client.DateTimeRangeFieldServerRpc;
import org.vaadin.addons.client.DateTimeRangeFieldState;
import org.vaadin.addons.type.DateTimeRange;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.UI;

// This is the server-side UI component that provides public API
@JavaScript({ "js/jquery.min.js", "js/moment-with-locales.min.js", "js/daterangepicker.js" })
public class DateTimeRangeField extends AbstractField<DateTimeRange> {

    private static final long serialVersionUID = 1L;

    private static final String EMPTY_STRING = "";

    private Format dateFormatter = null;

    // To process events from the client, we implement ServerRpc
    private final DateTimeRangeFieldServerRpc rpc = (from, to) -> setValue(new DateTimeRange(from, to));


    public DateTimeRangeField(final Format dateFormatter) {
        // To receive events from the client, we register ServerRpc
        registerRpc(this.rpc);

        getState().setLanguage(UI.getCurrent()
                               .getLocale()
                               .getLanguage());

        this.dateFormatter = dateFormatter;
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



    public DateTimeRangeField language(final String language) {
        getState().setLanguage(language);
        return this;
    }

    /*
     * The maximum span between the selected start and end dates. Can have any property you can add to a moment object (i.e. days, months)
     */
    public class DateLimit {
        private String dateLimitSpanMoment; // moment object, i.e days, month
        private int dateLimitSpanValue;

        public DateLimit(final String dateLimitSpanMoment, final int dateLimitSpanValue) {
            this.dateLimitSpanMoment = dateLimitSpanMoment;
            this.dateLimitSpanValue = dateLimitSpanValue;
        }

        public String getDateLimitSpanMoment() {
            return this.dateLimitSpanMoment;
        }

        public int getDateLimitSpanValue() {
            return this.dateLimitSpanValue;
        }
    }

    public DateTimeRangeField dateLimit(DateLimit dateLimit) {
        if (dateLimit != null) {
            getState().setDateLimitSpanMoment(dateLimit.getDateLimitSpanMoment());
            getState().setDateLimitSpanValue(dateLimit.getDateLimitSpanValue());
        } else {
            // Reset values
            getState().setDateLimitSpanMoment(DateTimeRangeField.EMPTY_STRING);
            getState().setDateLimitSpanValue(0);
        }
        return this;
    }

    public DateTimeRangeField applyLabel(final String applyLabel) {
        getState().setApplyLabel(applyLabel);
        return this;
    }

    public DateTimeRangeField canelLabel(final String cancelLabel) {
        getState().setCancelLabel(cancelLabel);
        return this;
    }

    public DateTimeRangeField buttonClasses(final String buttonClasses) {
        getState().setButtonClasses(buttonClasses);
        return this;
    }

    public DateTimeRangeField applyClass(final String applyClass) {
        getState().setApplyClass(applyClass);
        return this;
    }

    public DateTimeRangeField cancelClass(final String cancelClass) {
        getState().setCancelClass(cancelClass);
        return this;
    }

    public DateTimeRangeField alwaysShowCalendars(final boolean alwaysShowCalendars) {
        getState().setAlwaysShowCalendars(alwaysShowCalendars);
        return this;
    }

    public DateTimeRangeField showCustomRangeLabel(final boolean showCustomRangeLabel) {
        getState().setShowCustomRangeLabel(showCustomRangeLabel);
        return this;
    }

    public DateTimeRangeField parentEl(final String parentEl) {
        getState().setParentEl(parentEl);
        return this;
    }

    public DateTimeRangeField startDate(final Date startDate) {
        getState().setStartDate(formatDateToString(startDate));
        return this;
    }

    public DateTimeRangeField endDate(final Date endDate) {
        getState().setEndDate(formatDateToString(endDate));
        return this;
    }

    public DateTimeRangeField minDate(final Date minDate) {
        getState().setMinDate(formatDateToString(minDate));
        return this;
    }

    public DateTimeRangeField maxDate(final Date maxDate) {
        getState().setMaxDate(formatDateToString(maxDate));
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

    public Format getDateFormatter() {
        return this.dateFormatter;
    }

    private String formatDateToString(final Date date) {
        final String result = (date != null ? getDateFormatter().format(date) : DateTimeRangeField.EMPTY_STRING);
        return result;
    }
}
