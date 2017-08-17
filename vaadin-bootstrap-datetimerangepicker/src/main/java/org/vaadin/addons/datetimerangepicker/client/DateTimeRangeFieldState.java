package org.vaadin.addons.datetimerangepicker.client;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.vaadin.shared.AbstractFieldState;

//
// @see http://www.daterangepicker.com/#config
//
public class DateTimeRangeFieldState extends AbstractFieldState {

    private static final long serialVersionUID = 1L;
    static final String EMPTY_STRING = "";

    // Default values

    private String buttonClasses = "btn btn-sm";
    private String applyClass = "btn-success";
    private String cancelClass = "btn-default";

    private boolean alwaysShowCalendars = false;
    private boolean showCustomRangeLabel = false;

    private String parentEl = "body";

    private String startDate;
    private String endDate;

    private String minDate;
    private String maxDate;

    private String language = "en";

    private String applyLabel = "Apply";
    private String cancelLabel = "Cancel";

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
    private Locale locale;
    private boolean autoApply = false;
    private boolean linkedCalendars = true;
    private boolean autoUpdateInput = true;

    private String opens = "right";
    private String drops = "down";

    // dateLimit: (object) The maximum span between the selected start and end dates. Can have any property you can add to a moment object (i.e. days, months)
    private String dateLimitSpanMoment = DateTimeRangeFieldState.EMPTY_STRING;
    private int dateLimitSpanValue = 0;

    private Map<String, List<String>> dateRanges = new HashMap<String, List<String>>();

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public String getApplyLabel() {
        return this.applyLabel;
    }

    public void setApplyLabel(final String applyLabel) {
        this.applyLabel = applyLabel;
    }

    public String getCancelLabel() {
        return this.cancelLabel;
    }

    public void setCancelLabel(final String cancelLabel) {
        this.cancelLabel = cancelLabel;
    }

    public String getButtonClasses() {
        return this.buttonClasses;
    }

    public void setButtonClasses(final String buttonClasses) {
        this.buttonClasses = buttonClasses;
    }

    public String getApplyClass() {
        return this.applyClass;
    }

    public void setApplyClass(final String applyClass) {
        this.applyClass = applyClass;
    }

    public String getCancelClass() {
        return this.cancelClass;
    }

    public void setCancelClass(final String cancelClass) {
        this.cancelClass = cancelClass;
    }

    public boolean isAlwaysShowCalendars() {
        return this.alwaysShowCalendars;
    }

    public void setAlwaysShowCalendars(boolean alwaysShowCalendars) {
        this.alwaysShowCalendars = alwaysShowCalendars;
    }

    public boolean isShowCustomRangeLabel() {
        return this.showCustomRangeLabel;
    }

    public void setShowCustomRangeLabel(boolean showCustomRangeLabel) {
        this.showCustomRangeLabel = showCustomRangeLabel;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(final String endDate) {
        this.endDate = endDate;
    }

    public String getMinDate() {
        return this.minDate;
    }

    public void setMinDate(final String minDate) {
        this.minDate = minDate;
    }

    public String getMaxDate() {
        return this.maxDate;
    }

    public void setMaxDate(final String maxDate) {
        this.maxDate = maxDate;
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

    public String getParentEl() {
        return this.parentEl;
    }

    public void setParentEl(String parentEl) {
        this.parentEl = parentEl;
    }

    public void setDateLimitSpanMoment(String dateLimitSpanMoment) {
        this.dateLimitSpanMoment = dateLimitSpanMoment;
    }

    public String getDateLimitSpanMoment() {
        return this.dateLimitSpanMoment;
    }

    public int getDateLimitSpanValue() {
        return this.dateLimitSpanValue;
    }

    public void setDateLimitSpanValue(int dateLimitSpanValue) {
        this.dateLimitSpanValue = dateLimitSpanValue;
    }

    public Map<String, List<String>> getDateRanges() {
        return this.dateRanges;
    }

    public void setDateRanges(Map<String, List<String>> dateRanges) {
        this.dateRanges = dateRanges;
    }
}