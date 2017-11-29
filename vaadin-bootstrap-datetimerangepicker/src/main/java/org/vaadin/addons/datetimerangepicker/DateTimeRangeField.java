package org.vaadin.addons.datetimerangepicker;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.vaadin.addons.datetimerangepicker.client.DateTimeRangeFieldServerRpc;
import org.vaadin.addons.datetimerangepicker.client.DateTimeRangeFieldState;
import org.vaadin.addons.datetimerangepicker.type.DateTimeRange;
import org.vaadin.addons.datetimerangepicker.type.DateTimeRangeEnums;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.UI;

// This is the server-side UI component that provides public API
@JavaScript({ "js/jquery.min.js", "js/moment-with-locales.min.js", "js/daterangepicker.js" })
public class DateTimeRangeField extends AbstractField<DateTimeRange> {

    private static final long serialVersionUID = 1L;

    private static final String EMPTY_STRING = "";

    private Format dateFormatter = null;

    /**
     * The maximum span between the selected start and end dates. Can have any property you can add to a moment object (i.e. days, months)
     */
    public class DateLimit {
        private final String dateLimitSpanMoment; // moment object, i.e days, month
        private final int dateLimitSpanValue;

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

    @Override
    protected void setInternalValue(DateTimeRange newValue) {
        super.setInternalValue(newValue);
        startDate(newValue.getFrom());
        endDate(newValue.getTo());
    }

    /**
     * Set predefined date ranges the user can select from. Each key is the label for the range, and its value an array with two dates representing the bounds
     * of the range
     */
    public class Range {
        private final Date from, to;

        public Range(final Date from, final Date to) {
            this.from = from;
            this.to = to;
        }

        public Date getFrom() {
            return this.from;
        }

        public Date getTo() {
            return this.to;
        }
    }

    // To process events from the client, we implement ServerRpc
    private final DateTimeRangeFieldServerRpc rpc = new DateTimeRangeFieldServerRpc() {
        private static final long serialVersionUID = 1L;

        @Override
        public void valueChanged(Date from, Date to) {
            setValue(new DateTimeRange(from, to));
        }
    };

    /**
     * Constructor
     *
     * @param dateFormatter Formatter of given dates.
     * @param linkedCalendars When enabled, the two calendars displayed will always be for two sequential months (i.e. January and February), and both will be
     *            advanced when clicking the left or right arrows above the calendars. When disabled, the two calendars can be individually advanced and display
     *            any month/year.
     * @param autoUpdateInput Indicates whether the date range picker should automatically update the value of an input element it's attached to at
     *            initialization and when the selected dates change.
     */
    public DateTimeRangeField(final Format dateFormatter, boolean linkedCalendars, boolean autoUpdateInput) {
        // To receive events from the client, we register ServerRpc
        registerRpc(this.rpc);

        getState().setLanguage(UI.getCurrent()
            .getLocale()
            .getLanguage());
        getState().setLinkedCalendars(linkedCalendars);
        getState().setAutoUpdateInput(autoUpdateInput);
        getState().setDatePattern(((SimpleDateFormat) dateFormatter).toPattern());

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
        super.setLocale(locale);
        getState().setLocale(locale);
        this.dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        String datePattern = ((SimpleDateFormat) dateFormatter).toPattern();
        getState().setDatePattern(datePattern);
    }

    /**
     * @param language Language for localization of month, day and date format.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField language(final String language) {
        getState().setLanguage(language);
        return this;
    }

    /**
     * @param dateLimit The maximum span between the selected start and end dates. Can have any property you can add to a moment object (i.e. days, months)
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField dateLimit(DateLimit dateLimit) {
        if (dateLimit != null) {
            getState().setDateLimitSpanMoment(dateLimit.getDateLimitSpanMoment());
            getState().setDateLimitSpanValue(dateLimit.getDateLimitSpanValue());
        }
        else {
            // Reset values
            getState().setDateLimitSpanMoment(DateTimeRangeField.EMPTY_STRING);
            getState().setDateLimitSpanValue(0);
        }
        return this;
    }

    /**
     * @param ranges Set predefined date ranges the user can select from. Each key is the label for the range, and its value an array with two dates
     *            representing the bounds of the range
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField ranges(Map<String, Range> ranges) {
        Map<String, List<String>> dateRanges = new HashMap<>();

        for (Entry<String, Range> entry : ranges.entrySet()) {
            String rangeLabel = entry.getKey();
            String dateFromAsString = formatDateToString(entry.getValue()
                .getFrom());
            String dateToAsString = formatDateToString(entry.getValue()
                .getTo());

            List<String> dateRange = new ArrayList<>();
            dateRange.add(dateFromAsString);
            dateRange.add(dateToAsString);
            dateRanges.put(rangeLabel, dateRange);
        }
        getState().setDateRanges(dateRanges);
        return this;
    }

    /**
     * @param applyLabel Label of apply button.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField applyLabel(final String applyLabel) {
        getState().setApplyLabel(applyLabel);
        return this;
    }

    /**
     * @param cancelLabel Label of cancel button.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField cancelLabel(final String cancelLabel) {
        getState().setCancelLabel(cancelLabel);
        return this;
    }

    /**
     * @param buttonClasses CSS class names that will be added to all buttons in the picker.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField buttonClasses(final String buttonClasses) {
        getState().setButtonClasses(buttonClasses);
        return this;
    }

    /**
     * @param applyClass CSS class string that will be added to the apply button.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField applyClass(final String applyClass) {
        getState().setApplyClass(applyClass);
        return this;
    }

    /**
     * @param cancelClass CSS class string that will be added to the cancel button.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField cancelClass(final String cancelClass) {
        getState().setCancelClass(cancelClass);
        return this;
    }

    /**
     * @param alwaysShowCalendars Normally, if you use the ranges option to specify pre-defined date ranges, calendars for choosing a custom date range are not
     *            shown until the user clicks "Custom Range". When this option is set to true, the calendars for choosing a custom date range are always shown
     *            instead.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField alwaysShowCalendars(final boolean alwaysShowCalendars) {
        getState().setAlwaysShowCalendars(alwaysShowCalendars);
        return this;
    }

    /**
     * @param showCustomRangeLabel Displays an item labeled "Custom Range" at the end of the list of predefined ranges, when the ranges option is used. This
     *            option will be highlighted whenever the current date range selection does not match one of the predefined ranges. Clicking it will display the
     *            calendars to select a new range.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField showCustomRangeLabel(final boolean showCustomRangeLabel) {
        getState().setShowCustomRangeLabel(showCustomRangeLabel);
        return this;
    }

    /**
     * @param parentEl JQuery selector of the parent element that the date range picker will be added to, if not provided this will be 'body'.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField parentEl(final String parentEl) {
        getState().setParentEl(parentEl);
        return this;
    }

    /**
     * @param startDate The start of the initially selected date range.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField startDate(final Date startDate) {
        getState().setStartDate(formatDateToString(startDate));
        return this;
    }

    /**
     * @param endDate The end of the initially selected date range.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField endDate(final Date endDate) {
        getState().setEndDate(formatDateToString(endDate));
        return this;
    }

    /**
     * @param minDate The earliest date a user may select.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField minDate(final Date minDate) {
        getState().setMinDate(formatDateToString(minDate));
        return this;
    }

    /**
     * @param maxDate The latest date a user may select.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField maxDate(final Date maxDate) {
        getState().setMaxDate(formatDateToString(maxDate));
        return this;
    }

    /**
     * @param opens Whether the picker appears aligned to the left, to the right, or centered under the HTML element it's attached to.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField opens(final DateTimeRangeEnums.OPENS opens) {
        getState().setOpens(opens.toString());
        return this;
    }

    /**
     * @param drops Whether the picker appears below (default) or above the HTML element it's attached to.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField drops(final DateTimeRangeEnums.DROPS drops) {
        getState().setDrops(drops.toString());
        return this;
    }

    /**
     * @param showDropdowns Show year and month select boxes above calendars to jump to a specific month and year.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField showDropdowns(final boolean showDropdowns) {
        getState().setShowDropdowns(showDropdowns);
        return this;
    }

    /**
     * @param showWeekNumbers Show localized week numbers at the start of each week on the calendars.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField showWeekNumbers(final boolean showWeekNumbers) {
        getState().setShowWeekNumbers(showWeekNumbers);
        return this;
    }

    /**
     * @param showISOWeekNumbers Show ISO week numbers at the start of each week on the calendars.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField showISOWeekNumbers(final boolean showISOWeekNumbers) {
        getState().setShowISOWeekNumbers(showISOWeekNumbers);
        return this;
    }

    /**
     * @param singleDatePicker Show only a single calendar to choose one date, instead of a range picker with two calendars; the start and end dates provided to
     *            your callback will be the same single date chosen.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField singleDatePicker(final boolean singleDatePicker) {
        getState().setSingleDatePicker(singleDatePicker);
        return this;
    }

    /**
     * @param timePicker Allow selection of dates with times, not just dates.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField timePicker(final boolean timePicker) {
        getState().setTimePicker(timePicker);
        return this;
    }

    /**
     * @param timePicker24Hour Use 24-hour instead of 12-hour times, removing the AM/PM selection.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField timePicker24Hour(final boolean timePicker24Hour) {
        getState().setTimePicker24Hour(timePicker24Hour);
        return this;
    }

    /**
     * 
     * @param workable if the component is enabled or disabled.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField workable(final boolean workable) {
        getState().setWorkable(workable);
        super.setEnabled(workable);
        if (workable) {
            this.removeStyleName("v-disabled");
        }
        else {
            this.addStyleName("v-disabled");
        }
        return this;
    }

    /**
     * @param timePickerIncrement Increment of the minutes selection list for times (i.e. 30 to allow only selection of times ending in 0 or 30).
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField timePickerIncrement(final int timePickerIncrement) {
        getState().setTimePickerIncrement(timePickerIncrement);
        return this;
    }

    /**
     * @param timePickerSeconds Show seconds in the timePicker.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField timePickerSeconds(final boolean timePickerSeconds) {
        getState().setTimePickerSeconds(timePickerSeconds);
        return this;
    }

    /**
     * @param autoApply Hide the apply and cancel buttons, and automatically apply a new date range as soon as two dates or a predefined range is selected.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField autoApply(final boolean autoApply) {
        getState().setAutoApply(autoApply);
        return this;
    }

    /**
     * @param linkedCalendars When enabled, the two calendars displayed will always be for two sequential months (i.e. January and February), and both will be
     *            advanced when clicking the left or right arrows above the calendars. When disabled, the two calendars can be individually advanced and display
     *            any month/year.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField linkedCalendars(final boolean linkedCalendars) {
        getState().setLinkedCalendars(linkedCalendars);
        return this;
    }

    /**
     * @param autoUpdateInput Indicates whether the date range picker should automatically update the value of an input element it's attached to at
     *            initialization and when the selected dates change.
     * @return Instance of {@link DateTimeRangeField}
     */
    public DateTimeRangeField autoUpdateInput(final boolean autoUpdateInput) {
        getState().setAutoUpdateInput(autoUpdateInput);
        return this;
    }

    /**
     * @return DateFormatter, used formatting given data parameters.
     */
    public Format getDateFormatter() {
        return this.dateFormatter;
    }

    /**
     * @param date Date to format.
     * @return formatted date.
     */
    private String formatDateToString(final Date date) {
        final String result = (date != null ? getDateFormatter().format(date) : DateTimeRangeField.EMPTY_STRING);
        return result;
    }

}
