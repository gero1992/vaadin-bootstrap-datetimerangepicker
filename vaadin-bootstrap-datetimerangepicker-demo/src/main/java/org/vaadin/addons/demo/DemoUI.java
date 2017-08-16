package org.vaadin.addons.demo;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.DateTimeRangeField;
import org.vaadin.addons.demo.model.SomeBean;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@Title("MyComponent Add-on Demo")
@Widgetset("org.vaadin.addons.demo.DemoWidgetSet")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(
                value = "/*",
                asyncSupported = true)
    @VaadinServletConfiguration(
                                productionMode = false,
                                ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    private static final Locale LOCALE = Locale.ENGLISH;

    private static final String DATE_PATTERN = "MM/dd/YYYY";
    private static final Format DATE_FORMATTER = new SimpleDateFormat(DemoUI.DATE_PATTERN);

    private static final String APPLY_LABEL = "Apply";
    private static final String CANCEL_LABEL = "Cancel";

    private static final String DEFAULT_LANGUAGE = "en";

    // DateLimit Constants
    private static final String DATE_LIMIT_SPAN_MOMENT = "days";
    private static final int DATE_LIMIT_SPAN_VALUE = 7;

    // Range Constants
    private static final String RANGE_LIMIT_TODAY = "Today";
    private static final String RANGE_LIMIT_YESTERDAY = "Yesterday";

    // UI Components

    // Initialize our new UI component
    private DateTimeRangeField dateRangeField;

    private DateField startDateField;
    private DateField endDateField;
    private DateField minDateField;
    private DateField maxDateField;

    private final Date startDate = Date.from(LocalDate.now()
                                             .atStartOfDay(ZoneId.systemDefault())
                                             .toInstant());

    private final Date endDate = Date.from(LocalDate.now()
                                           .atStartOfDay(ZoneId.systemDefault())
                                           .toInstant());

    private ComboBox comboOpens;
    private ComboBox comboDrops;

    private CheckBox checkShowDropDowns;
    private CheckBox checkShowWeekNumbers;
    private CheckBox checkShowISOWeekNumbers;

    private CheckBox checkSingleDatePicker;
    private CheckBox checkTimePicker;
    private CheckBox checkTimePicker24Hour;

    private TextField textParentEl;
    private TextField textTimePickerIncrement;

    private CheckBox checkTimePickerSeconds;
    private CheckBox checkDateLimit;
    private CheckBox checkAutoApply;
    private CheckBox checkLinkedCalendars;
    private CheckBox checkAutoUpdateInput;

    private Button btnRefreshUI;

    private TextField textButtonClasses;
    private TextField textApplyClass;
    private TextField textCancelClass;

    private CheckBox checkRanges;
    private CheckBox checkAlwaysShowCalendars;
    private CheckBox checkShowCustomRangeLabel;

    @Override
    protected void init(final VaadinRequest request) {

        // Initialize our new UI component
        boolean linkedCalendars = true;
        boolean autoUpdateInput = true;
        this.dateRangeField = new DateTimeRangeField(DemoUI.DATE_FORMATTER, linkedCalendars, autoUpdateInput);

        final BeanFieldGroup<SomeBean> fieldGroup = new BeanFieldGroup<>(SomeBean.class);
        fieldGroup.setBuffered(false);
        fieldGroup.setItemDataSource(new SomeBean());
        fieldGroup.bind(this.dateRangeField, "dateRange");

        // ParentEl

        this.textParentEl = new TextField("parentEl");

        // StartDate
        this.startDateField = new DateField("startDate");
        this.startDateField.setLocale(DemoUI.LOCALE);
        this.startDateField.setValue(this.startDate);

        // EndDate
        this.endDateField = new DateField("endDate");
        this.endDateField.setLocale(DemoUI.LOCALE);
        this.endDateField.setValue(this.endDate);

        // MinDate
        this.minDateField = new DateField("minDate");
        this.minDateField.setLocale(DemoUI.LOCALE);

        // MaxDate
        this.maxDateField = new DateField("maxDate");
        this.maxDateField.setLocale(DemoUI.LOCALE);

        // Combobox opens
        this.comboOpens = new ComboBox("opens");
        final java.util.List<Object> opensList = java.util.Arrays.asList("right", "left", "center");
        this.comboOpens.addItem(opensList.get(0));
        this.comboOpens.addItem(opensList.get(1));
        this.comboOpens.addItem(opensList.get(2));
        this.comboOpens.setNullSelectionAllowed(false);
        this.comboOpens.setValue(opensList.get(0));

        // Combobox drops
        final java.util.List<Object> dropsList = java.util.Arrays.asList("down", "up");
        this.comboDrops = new ComboBox("drops");
        this.comboDrops.addItem(dropsList.get(0));
        this.comboDrops.addItem(dropsList.get(1));
        this.comboDrops.setNullSelectionAllowed(false);
        this.comboDrops.setValue(dropsList.get(0));

        // Checkbox showDropdowns
        this.checkShowDropDowns = new CheckBox("showDropdowns");
        this.checkShowDropDowns.setValue(false);

        // Checkbox showWeekNumbers
        this.checkShowWeekNumbers = new CheckBox("showWeekNumbers");
        this.checkShowWeekNumbers.setValue(false);

        // Checkbox showISOWeekNumbers
        this.checkShowISOWeekNumbers = new CheckBox("showISOWeekNumbers");
        this.checkShowISOWeekNumbers.setValue(false);

        // Checkbox singleDatePicker
        this.checkSingleDatePicker = new CheckBox("singleDatePicker");
        this.checkSingleDatePicker.setValue(false);

        // Checkbox timePicker
        this.checkTimePicker = new CheckBox("timePicker");
        this.checkTimePicker.setValue(false);

        // Checkbox timePicker24Hour
        this.checkTimePicker24Hour = new CheckBox("timePicker24Hour");
        this.checkTimePicker24Hour.setValue(false);

        this.textTimePickerIncrement = new TextField("timePickerIncrement (in minutes)");
        this.textTimePickerIncrement.setValue(String.valueOf(1));

        // Checkbox timePickerSeconds
        this.checkTimePickerSeconds = new CheckBox("timePickerSeconds");
        this.checkTimePickerSeconds.setValue(false);

        // Checkbox dateRange
        this.checkDateLimit = new CheckBox("dateLimit (with example date range span)");
        this.checkDateLimit.setValue(false);

        // Checkbox autoApply
        this.checkAutoApply = new CheckBox("autoApply");
        this.checkAutoApply.setValue(false);

        // Checkbox linkedCalendars
        this.checkLinkedCalendars = new CheckBox("linkedCalendars");
        this.checkLinkedCalendars.setValue(true);

        // Checkbox autoUpdateInput
        this.checkAutoUpdateInput = new CheckBox("autoUpdateInput");
        this.checkAutoUpdateInput.setValue(true);

        // Textfield buttonClasses
        this.textButtonClasses = new TextField("buttonClasses");
        this.textButtonClasses.setValue("btn btn-sm");

        // Textfield applyClass
        this.textApplyClass = new TextField("applyClass");
        this.textApplyClass.setValue("btn-success");

        // Textfield cancelClass
        this.textCancelClass = new TextField("cancelClass");
        this.textCancelClass.setValue("btn-default");

        // Checkbox checkRanges
        this.checkRanges = new CheckBox("ranges (with example predefined ranges)");
        this.checkRanges.setValue(false);

        // Checkbox alwaysShowCalendars
        this.checkAlwaysShowCalendars = new CheckBox("alwaysShowCalendars");
        this.checkAlwaysShowCalendars.setValue(false);

        // Checkbox showCustomRangeLabel
        this.checkShowCustomRangeLabel = new CheckBox("showCustomRangeLabel");
        this.checkShowCustomRangeLabel.setValue(false);

        // Button buildUI

        this.btnRefreshUI = new Button("refreshUI");
        this.btnRefreshUI.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                // DateLimit
                DateTimeRangeField.DateLimit dateLimit = null;
                if (DemoUI.this.checkDateLimit.getValue()) {
                    dateLimit = DemoUI.this.buildDateLimit(DemoUI.DATE_LIMIT_SPAN_MOMENT, DemoUI.DATE_LIMIT_SPAN_VALUE);
                }
                // Ranges
                Map<String, DateTimeRangeField.Range> ranges = new HashMap<>();
                if (DemoUI.this.checkRanges.getValue()) {
                    DateTimeRangeField.Range rangeToday = DemoUI.this.buildRange(DemoUI.this.startDate, DemoUI.this.endDate);
                    ranges.put(DemoUI.RANGE_LIMIT_TODAY, rangeToday);
                    DateTimeRangeField.Range rangeYesterday = DemoUI.this.buildRange(DemoUI.this.startDate, DemoUI.this.endDate);
                    ranges.put(DemoUI.RANGE_LIMIT_YESTERDAY, rangeYesterday);
                }

                // Others
                DemoUI.this.dateRangeField.parentEl(DemoUI.this.textParentEl.getValue())
                .startDate(DemoUI.this.startDateField.getValue())
                .endDate(DemoUI.this.endDateField.getValue())
                .minDate(DemoUI.this.minDateField.getValue())
                .maxDate(DemoUI.this.maxDateField.getValue())
                .applyLabel(DemoUI.APPLY_LABEL)
                .canelLabel(DemoUI.CANCEL_LABEL)
                .opens(DemoUI.this.comboOpens.getValue()
                       .toString())
                .language(DemoUI.DEFAULT_LANGUAGE)
                .drops(DemoUI.this.comboDrops.getValue()
                       .toString())
                .showDropdowns(DemoUI.this.checkShowDropDowns.getValue())
                .showWeekNumbers(DemoUI.this.checkShowWeekNumbers.getValue())
                .showISOWeekNumbers(DemoUI.this.checkShowISOWeekNumbers.getValue())
                .singleDatePicker(DemoUI.this.checkSingleDatePicker.getValue())
                .timePicker(DemoUI.this.checkTimePicker.getValue())
                .timePicker24Hour(DemoUI.this.checkTimePicker24Hour.getValue())
                .timePickerIncrement(Integer.valueOf(DemoUI.this.textTimePickerIncrement.getValue()))
                .timePickerSeconds(DemoUI.this.checkTimePickerSeconds.getValue())
                .dateLimit(dateLimit)
                .autoApply(DemoUI.this.checkAutoApply.getValue())
                .linkedCalendars(DemoUI.this.checkLinkedCalendars.getValue())
                .autoUpdateInput(DemoUI.this.checkAutoUpdateInput.getValue())
                .buttonClasses(DemoUI.this.textButtonClasses.getValue())
                .applyClass(DemoUI.this.textApplyClass.getValue())
                .cancelClass(DemoUI.this.textCancelClass.getValue())
                .ranges(ranges)
                .alwaysShowCalendars(DemoUI.this.checkAlwaysShowCalendars.getValue())
                .showCustomRangeLabel(DemoUI.this.checkShowCustomRangeLabel.getValue())
                .refresh();
            }
        });

        // Button show

        final Button button = new Button("show");
        button.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                Notification.show(fieldGroup.getItemDataSource()
                                  .getBean()
                                  .getDateRange()
                                  .toString());
            }
        });

        final VerticalLayout elementsLayout = new VerticalLayout();
        elementsLayout.setSpacing(true);

        final GridLayout grid = new GridLayout(3, 15);

        // Column Left

        grid.addComponent(this.textParentEl, 0, 0);
        grid.addComponent(this.startDateField, 0, 1);
        grid.addComponent(this.endDateField, 0, 2);
        grid.addComponent(this.minDateField, 0, 3);
        grid.addComponent(this.maxDateField, 0, 4);
        grid.addComponent(this.comboOpens, 0, 5);
        grid.addComponent(this.comboDrops, 0, 6);

        // Column Middle

        grid.addComponent(this.checkShowDropDowns, 1, 0);
        grid.addComponent(this.checkShowWeekNumbers, 1, 1);
        grid.addComponent(this.checkShowISOWeekNumbers, 1, 2);
        grid.addComponent(this.checkSingleDatePicker, 1, 3);
        grid.addComponent(this.checkTimePicker, 1, 4);
        grid.addComponent(this.checkTimePicker24Hour, 1, 5);
        grid.addComponent(this.textTimePickerIncrement, 1, 6);
        grid.addComponent(this.checkTimePickerSeconds, 1, 7);
        grid.addComponent(this.checkDateLimit, 1, 8);
        grid.addComponent(this.checkAutoApply, 1, 9);
        grid.addComponent(this.checkLinkedCalendars, 1, 10);
        grid.addComponent(this.checkAutoUpdateInput, 1, 11);
        grid.addComponent(this.btnRefreshUI, 1, 12);

        //
        // DateRangeField
        //
        grid.addComponent(this.dateRangeField, 1, 13);
        grid.addComponent(button, 1, 14);

        // Column Right

        grid.addComponent(this.textButtonClasses, 2, 0);
        grid.addComponent(this.textApplyClass, 2, 1);
        grid.addComponent(this.textCancelClass, 2, 2);
        grid.addComponent(this.checkRanges, 2, 3);
        grid.addComponent(this.checkAlwaysShowCalendars, 2, 4);
        grid.addComponent(this.checkShowCustomRangeLabel, 2, 5);

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.addComponent(grid);
        layout.setComponentAlignment(grid, Alignment.TOP_CENTER);

        setContent(layout);
    }

    /**
     * Builds dateLimit with given date range span.
     *
     * @param dateLimitSpanMoment
     * @param dateLimitSpanValue
     * @return {@link DateTimeRangeField.DateLimit}
     */
    private DateTimeRangeField.DateLimit buildDateLimit(String dateLimitSpanMoment, int dateLimitSpanValue) {
        DateTimeRangeField.DateLimit dateLimit = DemoUI.this.dateRangeField.new DateLimit(dateLimitSpanMoment, dateLimitSpanValue);
        return dateLimit;
    }

    /**
     * Builds ranges with given range.
     *
     * @param label Label for the range
     * @param from
     * @param to
     * @return {@link DateTimeRangeField.Range}
     */
    private DateTimeRangeField.Range buildRange(Date from, Date to) {
        DateTimeRangeField.Range range = DemoUI.this.dateRangeField.new Range(from, to);
        return range;
    }
}
