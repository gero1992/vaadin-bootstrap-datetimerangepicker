package org.vaadin.addons.client;

import java.util.Locale;

import com.vaadin.shared.AbstractFieldState;

public class DateTimeRangeFieldState extends AbstractFieldState {

    private static final long serialVersionUID = 1L;

    //
    private boolean showDropdowns = true;
    private boolean showWeekNumbers = true;
    private boolean showISOWeekNumbers = true;
    private boolean singleDatePicker = true;
    private boolean timePicker = true;
    private boolean timePicker24Hour = true;
    //
    private int timePickerIncrement = -1;
    //
    private boolean timePickerSeconds = true;
    private int dateLimit = 7;
    private Locale locale = null;
    private boolean autoApply = true;
    private boolean linkedCalendars = true;
    private boolean autoUpdateInput = true;

    public boolean isShowWeekNumbers() {
        return this.showWeekNumbers;
    }

    public void setShowWeekNumbers(final boolean showWeekNumbers) {
        this.showWeekNumbers = showWeekNumbers;
    }

    public boolean isShowISOWeekNumbers() {
        return this.showISOWeekNumbers;
    }

    public void setShowISOWeekNumbers(final boolean showISOWeekNumbers) {
        this.showISOWeekNumbers = showISOWeekNumbers;
    }

    public boolean isSingleDatePicker() {
        return this.singleDatePicker;
    }

    public void setSingleDatePicker(final boolean singleDatePicker) {
        this.singleDatePicker = singleDatePicker;
    }

    public boolean isTimePicker() {
        return this.timePicker;
    }

    public void setTimePicker(final boolean timePicker) {
        this.timePicker = timePicker;
    }

    public boolean isTimePicker24Hour() {
        return this.timePicker24Hour;
    }

    public void setTimePicker24Hour(final boolean timePicker24Hour) {
        this.timePicker24Hour = timePicker24Hour;
    }

    public int getTimePickerIncrement() {
        return this.timePickerIncrement;
    }

    public void setTimePickerIncrement(final int timePickerIncrement) {
        this.timePickerIncrement = timePickerIncrement;
    }

    public boolean isTimePickerSeconds() {
        return this.timePickerSeconds;
    }

    public void setTimePickerSeconds(final boolean timePickerSeconds) {
        this.timePickerSeconds = timePickerSeconds;
    }

    public int getDateLimit() {
        return this.dateLimit;
    }

    public void setDateLimit(final int dateLimit) {
        this.dateLimit = dateLimit;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

    public boolean isAutoApply() {
        return this.autoApply;
    }

    public void setAutoApply(final boolean autoApply) {
        this.autoApply = autoApply;
    }

    public boolean isLinkedCalendars() {
        return this.linkedCalendars;
    }

    public void setLinkedCalendars(final boolean linkedCalendars) {
        this.linkedCalendars = linkedCalendars;
    }

    public boolean isAutoUpdateInput() {
        return this.autoUpdateInput;
    }

    public void setAutoUpdateInput(final boolean autoUpdateInput) {
        this.autoUpdateInput = autoUpdateInput;
    }

    public boolean isShowDropdowns() {
        return this.showDropdowns;
    }

    public void setShowDropdowns(final boolean showDropDowns) {
        this.showDropdowns = showDropDowns;
    }
}