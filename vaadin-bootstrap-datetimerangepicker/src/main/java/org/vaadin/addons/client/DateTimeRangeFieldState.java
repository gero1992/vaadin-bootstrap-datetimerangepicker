package org.vaadin.addons.client;

import java.util.Date;
import java.util.Locale;

import com.vaadin.shared.AbstractFieldState;

public class DateTimeRangeFieldState extends AbstractFieldState {

    private static final long serialVersionUID = 1L;

    private Date startDate;
    private Date endDate;

    //
    private boolean showDropdowns = false;
    private boolean showWeekNumbers = false;
    private boolean showISOWeekNumbers = false;
    private boolean singleDatePicker = false;
    private boolean timePicker = false;
    private boolean timePicker24Hour = false;
    //
    private int timePickerIncrement = 1;
    //
    private boolean timePickerSeconds = false;
    private int dateLimit = 7;
    private Locale locale = null;
    private boolean autoApply = false;
    private boolean linkedCalendars = false;
    private boolean autoUpdateInput = false;

    // Default values
    private String opens = "right";
    private String drops = "down";

    private boolean toggleState = false;

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    // Allowed values ("right" | "left" | "center") 
    public void setOpens(final String opens) {
        this.opens = opens;
    }

    public String getOpens() {
        return this.opens;
    }

    // Allowed values ("down" | "up") 
    public void setDrops(final String drops) {
        this.drops = drops;
    }

    public String getDrops() {
        return this.drops;
    }

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

    public void toggleState() {
        this.toggleState = !this.toggleState;
    }
}