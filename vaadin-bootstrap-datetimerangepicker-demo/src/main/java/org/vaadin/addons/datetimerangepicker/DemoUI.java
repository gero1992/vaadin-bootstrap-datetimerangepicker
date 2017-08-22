package org.vaadin.addons.datetimerangepicker;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.datetimerangepicker.demo.model.DateTimeRangeBean;
import org.vaadin.addons.datetimerangepicker.type.DateTimeRangeEnums;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@Title("MyComponent Add-on Demo")
@Widgetset("org.vaadin.addons.datetimerangepicker.demo.DemoWidgetSet")
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

    private final Date startDate = Date.from(LocalDate.now().minusDays(6)
                                             .atStartOfDay(ZoneId.systemDefault())
                                             .toInstant());

    private final Date endDate = Date.from(LocalDate.now()
                                           .atStartOfDay(ZoneId.systemDefault())
                                           .toInstant());

    private final Date today = Date.from(LocalDate.now()
                                         .atStartOfDay(ZoneId.systemDefault())
                                         .toInstant());

    private final Date yesterday = Date.from(LocalDate.now()
                                             .minusDays(1)
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
        this.dateRangeField = new DateTimeRangeField(DemoUI.DATE_FORMATTER, linkedCalendars, autoUpdateInput).startDate(this.startDate).endDate(this.endDate).language(DemoUI.DEFAULT_LANGUAGE);

        final BeanFieldGroup<DateTimeRangeBean> fieldGroup = new BeanFieldGroup<>(DateTimeRangeBean.class);
        fieldGroup.setBuffered(false);
        fieldGroup.setItemDataSource(new DateTimeRangeBean());
        fieldGroup.bind(this.dateRangeField, "dateRange");

        ValueChangeListener valueChangeListener = new ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                DateTimeRangeField.DateLimit dateLimit = null;
                if (DemoUI.this.checkDateLimit.getValue()) {
                    dateLimit = DemoUI.this.buildDateLimit(DemoUI.DATE_LIMIT_SPAN_MOMENT, DemoUI.DATE_LIMIT_SPAN_VALUE);
                }
                // Ranges
                Map<String, DateTimeRangeField.Range> ranges = new HashMap<>();
                if (DemoUI.this.checkRanges.getValue()) {
                    DateTimeRangeField.Range rangeToday = DemoUI.this.buildRange(DemoUI.this.today, DemoUI.this.today);
                    ranges.put(DemoUI.RANGE_LIMIT_TODAY, rangeToday);
                    DateTimeRangeField.Range rangeYesterday = DemoUI.this.buildRange(DemoUI.this.yesterday, DemoUI.this.yesterday);
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
                .opens(DateTimeRangeEnums.OPENS.valueOf(DemoUI.this.comboOpens.getValue().toString().toUpperCase()))
                .language(DemoUI.DEFAULT_LANGUAGE)
                .drops(DateTimeRangeEnums.DROPS.valueOf(DemoUI.this.comboDrops.getValue().toString().toUpperCase()))
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
                .showCustomRangeLabel(DemoUI.this.checkShowCustomRangeLabel.getValue());
            }
        };

        // ParentEl
        this.textParentEl = new TextField("parentEl");
        this.textParentEl.addValueChangeListener(valueChangeListener);

        // StartDate
        this.startDateField = new DateField("startDate");
        this.startDateField.setLocale(DemoUI.LOCALE);
        this.startDateField.setValue(this.startDate);
        this.startDateField.addValueChangeListener(valueChangeListener);

        // EndDate
        this.endDateField = new DateField("endDate");
        this.endDateField.setLocale(DemoUI.LOCALE);
        this.endDateField.setValue(this.endDate);
        this.endDateField.addValueChangeListener(valueChangeListener);

        // MinDate
        this.minDateField = new DateField("minDate");
        this.minDateField.setLocale(DemoUI.LOCALE);
        this.minDateField.addValueChangeListener(valueChangeListener);

        // MaxDate
        this.maxDateField = new DateField("maxDate");
        this.maxDateField.setLocale(DemoUI.LOCALE);
        this.maxDateField.addValueChangeListener(valueChangeListener);

        // Combobox opens
        this.comboOpens = new ComboBox("opens");
        this.comboOpens.addItems(DateTimeRangeEnums.OPENS.values());
        this.comboOpens.setNullSelectionAllowed(false);
        this.comboOpens.setValue(DateTimeRangeEnums.OPENS.RIGHT);
        this.comboOpens.addValueChangeListener(valueChangeListener);

        // Combobox drops
        this.comboDrops = new ComboBox("drops");
        this.comboDrops.addItems(DateTimeRangeEnums.DROPS.values());
        this.comboDrops.setNullSelectionAllowed(false);
        this.comboDrops.setValue(DateTimeRangeEnums.DROPS.DOWN);
        this.comboDrops.addValueChangeListener(valueChangeListener);

        // Checkbox showDropdowns
        this.checkShowDropDowns = new CheckBox("showDropdowns");
        this.checkShowDropDowns.setValue(false);
        this.checkShowDropDowns.addValueChangeListener(valueChangeListener);

        // Checkbox showWeekNumbers
        this.checkShowWeekNumbers = new CheckBox("showWeekNumbers");
        this.checkShowWeekNumbers.setValue(false);
        this.checkShowWeekNumbers.addValueChangeListener(valueChangeListener);

        // Checkbox showISOWeekNumbers
        this.checkShowISOWeekNumbers = new CheckBox("showISOWeekNumbers");
        this.checkShowISOWeekNumbers.setValue(false);
        this.checkShowISOWeekNumbers.addValueChangeListener(valueChangeListener);

        // Checkbox singleDatePicker
        this.checkSingleDatePicker = new CheckBox("singleDatePicker");
        this.checkSingleDatePicker.setValue(false);
        this.checkSingleDatePicker.addValueChangeListener(valueChangeListener);

        // Checkbox timePicker
        this.checkTimePicker = new CheckBox("timePicker");
        this.checkTimePicker.setValue(false);
        this.checkTimePicker.addValueChangeListener(valueChangeListener);

        // Checkbox timePicker24Hour
        this.checkTimePicker24Hour = new CheckBox("timePicker24Hour");
        this.checkTimePicker24Hour.setValue(false);
        this.checkTimePicker24Hour.addValueChangeListener(valueChangeListener);

        this.textTimePickerIncrement = new TextField("timePickerIncrement (in minutes)");
        this.textTimePickerIncrement.setValue(String.valueOf(1));
        this.textTimePickerIncrement.addValueChangeListener(valueChangeListener);

        // Checkbox timePickerSeconds
        this.checkTimePickerSeconds = new CheckBox("timePickerSeconds");
        this.checkTimePickerSeconds.setValue(false);
        this.checkTimePickerSeconds.addValueChangeListener(valueChangeListener);

        // Checkbox dateRange
        this.checkDateLimit = new CheckBox("dateLimit (with example date range span)");
        this.checkDateLimit.setValue(false);
        this.checkDateLimit.addValueChangeListener(valueChangeListener);

        // Checkbox autoApply
        this.checkAutoApply = new CheckBox("autoApply");
        this.checkAutoApply.setValue(false);
        this.checkAutoApply.addValueChangeListener(valueChangeListener);

        // Checkbox linkedCalendars
        this.checkLinkedCalendars = new CheckBox("linkedCalendars");
        this.checkLinkedCalendars.setValue(true);
        this.checkLinkedCalendars.addValueChangeListener(valueChangeListener);

        // Checkbox autoUpdateInput
        this.checkAutoUpdateInput = new CheckBox("autoUpdateInput");
        this.checkAutoUpdateInput.setValue(true);
        this.checkAutoUpdateInput.addValueChangeListener(valueChangeListener);

        // Textfield buttonClasses
        this.textButtonClasses = new TextField("buttonClasses");
        this.textButtonClasses.setValue("btn btn-sm");
        this.textButtonClasses.addValueChangeListener(valueChangeListener);

        // Textfield applyClass
        this.textApplyClass = new TextField("applyClass");
        this.textApplyClass.setValue("btn-success");
        this.textApplyClass.addValueChangeListener(valueChangeListener);

        // Textfield cancelClass
        this.textCancelClass = new TextField("cancelClass");
        this.textCancelClass.setValue("btn-default");
        this.textCancelClass.addValueChangeListener(valueChangeListener);

        // Checkbox checkRanges
        this.checkRanges = new CheckBox("ranges (with example predefined ranges)");
        this.checkRanges.setValue(false);
        this.checkRanges.addValueChangeListener(valueChangeListener);

        // Checkbox alwaysShowCalendars
        this.checkAlwaysShowCalendars = new CheckBox("alwaysShowCalendars");
        this.checkAlwaysShowCalendars.setValue(false);
        this.checkAlwaysShowCalendars.addValueChangeListener(valueChangeListener);

        // Checkbox showCustomRangeLabel
        this.checkShowCustomRangeLabel = new CheckBox("showCustomRangeLabel");
        this.checkShowCustomRangeLabel.setValue(false);
        this.checkShowCustomRangeLabel.addValueChangeListener(valueChangeListener);

        // Button show

        final Button button = new Button("Show value");
        button.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                Notification.show(fieldGroup.getItemDataSource()
                                  .getBean()
                                  .getDateRange()
                                  .toString());
            }
        });

        VerticalLayout leftLayout = new VerticalLayout();
        leftLayout.setSpacing(true);
        leftLayout.addComponent(this.textParentEl);
        leftLayout.addComponent(this.startDateField);
        leftLayout.addComponent(this.endDateField);
        leftLayout.addComponent(this.minDateField);
        leftLayout.addComponent(this.maxDateField);
        leftLayout.addComponent(this.comboOpens);
        leftLayout.addComponent(this.comboDrops);

        VerticalLayout middleLayout = new VerticalLayout();
        middleLayout.setSpacing(true);
        middleLayout.addComponent(this.checkShowDropDowns);
        middleLayout.addComponent(this.checkShowWeekNumbers);
        middleLayout.addComponent(this.checkShowISOWeekNumbers);
        middleLayout.addComponent(this.checkSingleDatePicker);
        middleLayout.addComponent(this.checkTimePicker);
        middleLayout.addComponent(this.checkTimePicker24Hour);
        middleLayout.addComponent(this.textTimePickerIncrement);
        middleLayout.addComponent(this.checkTimePickerSeconds);
        middleLayout.addComponent(this.checkDateLimit);
        middleLayout.addComponent(this.checkAutoApply);
        middleLayout.addComponent(this.checkLinkedCalendars);
        middleLayout.addComponent(this.checkAutoUpdateInput);

        VerticalLayout rightLayout = new VerticalLayout();
        rightLayout.setSpacing(true);
        rightLayout.addComponent(this.textButtonClasses);
        rightLayout.addComponent(this.textApplyClass);
        rightLayout.addComponent(this.textCancelClass);
        rightLayout.addComponent(this.checkRanges);
        rightLayout.addComponent(this.checkAlwaysShowCalendars);
        rightLayout.addComponent(this.checkShowCustomRangeLabel);

        HorizontalLayout settingsLayout = new HorizontalLayout();
        settingsLayout.setCaption("Settings");
        settingsLayout.setSpacing(true);
        settingsLayout.setMargin(true);
        settingsLayout.addComponent(leftLayout);
        settingsLayout.addComponent(middleLayout);
        settingsLayout.addComponent(rightLayout);

        VerticalLayout componentLayout = new VerticalLayout();
        componentLayout.setCaption("DateTimeRangeField");
        componentLayout.setSpacing(true);
        componentLayout.setMargin(true);
        componentLayout.addComponent(this.dateRangeField);
        componentLayout.addComponent(button);

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setStyleName("demoContentLayout");
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);
        mainLayout.addComponent(componentLayout);
        mainLayout.addComponent(settingsLayout);
        mainLayout.setExpandRatio(componentLayout, 0.2f);
        mainLayout.setExpandRatio(settingsLayout, 0.8f);

        setContent(mainLayout);
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
