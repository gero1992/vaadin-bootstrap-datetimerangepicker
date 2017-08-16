package org.vaadin.addons.client;

import java.util.Map;

import com.google.gwt.core.client.JsDate;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.TextBoxBase;

// Extend any GWT Widget
public class VDateTimeRangeField extends TextBoxBase {

    private static final String EMPTY_STRING = "";

    private DateRangeFieldClientUpdateValueHandler updateValueHandler;

    /*
     * Constructor. Ensures that needed html templates are added and injects a <database-visualizer> element to the page.
     */
    public VDateTimeRangeField() {
        this(DOM.createInputText());
    }

    protected VDateTimeRangeField(final Element node) {
        super(node);
        setStyleName("vaadin-bootstrap-datetimerangepicker");

        final int timePickerIncrement = 1;
        final String dateLimit = "{}";
        final String ranges = "{}";
        init(getElement(), "de", "body", VDateTimeRangeField.EMPTY_STRING, VDateTimeRangeField.EMPTY_STRING, VDateTimeRangeField.EMPTY_STRING,
             VDateTimeRangeField.EMPTY_STRING, false, false, false, false, false, false, timePickerIncrement, false, dateLimit, false, false, false, "rights",
             "down", VDateTimeRangeField.EMPTY_STRING, VDateTimeRangeField.EMPTY_STRING, VDateTimeRangeField.EMPTY_STRING, ranges, false, false);
    }

    public void setUpdateValueHandler(final DateRangeFieldClientUpdateValueHandler updateValueHandler) {
        this.updateValueHandler = updateValueHandler;
    }

    private native void init(Element node, String language, String parentEl, String startDate, String endDate, String minDate, String maxDate,
            boolean showDropdowns, boolean showWeekNumbers, boolean showISOWeekNumbers, boolean singleDatePicker, boolean timePicker, boolean timePicker24Hour,
            int timePickerIncrement, boolean timePickerSeconds, String dateLimit, boolean autoApply, boolean linkedCalendars, boolean autoUpdateInput,
            String opens, String drops, String buttonClasses, String applyClass, String cancelClass, String ranges, boolean alwaysShowCalendars,
            boolean showCustomRangeLabel) /*-{
                                          var _this = this;

                                          alert("init language: " + language)

                                          $wnd.moment.locale(language);

                                          alert("init autoApply: " + autoApply)
                                          alert("init startDate: " + startDate)
                                          alert("init endDate: " + endDate)
                                          alert("init dateLimitAsString: " + dateLimit)
                                          alert("init dateLimitAsJSon: " + JSON.parse(dateLimit))
                                          alert("init rangesAsString: " + ranges)
                                          alert("init rangesAsJSon: " + JSON.parse(ranges))

                                          configString = '{' +
                                          '"showDropdowns": ' + showDropdowns + ',' +
                                          '"showWeekNumbers": ' + showWeekNumbers + ',' +
                                          '"showISOWeekNumbers": ' + showISOWeekNumbers + ',' +
                                          '"singleDatePicker": ' + singleDatePicker + ',' +
                                          '"timePicker": ' + timePicker + ',' +
                                          '"timePicker24Hour": ' + timePicker24Hour + ',' +
                                          '"timePickerIncrement": ' + timePickerIncrement + ',' +
                                          '"timePickerSeconds": ' + timePickerSeconds + ',' +
                                          '"autoApply": ' + autoApply + ',' +
                                          '"linkedCalendars": ' + linkedCalendars + ',' +
                                          '"autoUpdateInput": ' + autoUpdateInput + ',' +
                                          '"opens": "' + opens + '",' +
                                          '"drops": "' + drops + '",' +
                                          '"parentEl": "' + parentEl + '",' +
                                          (typeof(startDate) === 'undefined' ? '' : '"startDate": "' + startDate + '",') +
                                          (typeof(endDate) === 'undefined' ? '' : '"endDate": "' + endDate + '",') +
                                          (typeof(minDate) === 'undefined' || minDate.length == 0 ? '' : '"minDate": "' + minDate + '",') +
                                          (typeof(maxDate) === 'undefined' || maxDate.length == 0 ? '' : '"maxDate": "' + maxDate + '",') +
                                          '"buttonClasses": "' + buttonClasses + '",' +
                                          '"applyClass": "' + applyClass + '",' +
                                          '"cancelClass": "' + cancelClass + '",' +
                                          '"alwaysShowCalendars": ' + alwaysShowCalendars + ',' +
                                          '"showCustomRangeLabel": ' + showCustomRangeLabel +
                                          '}';

                                          console.log(configString);
                                          console.log(JSON.parse(configString));

                                          $wnd.$(node).daterangepicker(JSON.parse(configString),
                                          function(start, end, label) {
                                          _this.@org.vaadin.addons.client.VDateTimeRangeField::onUpdateValue(Lcom/google/gwt/core/client/JsDate;Lcom/google/gwt/core/client/JsDate;)(start.toDate(),end.toDate());
                                          });

                                          $wnd.$(node).on('apply.daterangepicker', function(ev, picker) {
                                          $wnd.$(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
                                          });
                                          }-*/;

    private void onUpdateValue(final JsDate start, final JsDate end) {
        this.updateValueHandler.onUpdateValue(start, end);
    }

    public static interface DateRangeFieldClientUpdateValueHandler extends EventHandler {
        void onUpdateValue(JsDate start, JsDate end);
    }

    public void refresh(final String language, final String applyLabel, final String cancelLabel, final String parentEl, final String startDate,
            final String endDate, final String minDate, final String maxDate, final boolean showDropdowns, final boolean showWeekNumbers,
            final boolean showISOWeekNumbers, final boolean singleDatePicker, final boolean timePicker, final boolean timePicker24Hour,
            final int timePickerIncrement, final boolean timePickerSeconds, final String dateLimitSpanMoment, final int dateLimitSpanValue,
            final boolean autoApply, final boolean linkedCalendars, final boolean autoUpdateInput, final String opens, final String drops,
            final String buttonClasses, final String applyClass, final String cancelClass, final Map<String, DateRange> dateRanges, final boolean alwaysShowCalendars,
            final boolean showCustomRangeLabels) {

        // DateLimit Processing
        String dateLimit = "{}";
        if (dateLimitSpanMoment != null && !dateLimitSpanMoment.equals(VDateTimeRangeField.EMPTY_STRING)) {
            dateLimit = new StringBuilder().append("{ \"")
                    .append(dateLimitSpanMoment)
                    .append("\": ).append(dateLimitSpanValue).append( }")
                    .toString();
        }

        // Ranges Processing
        String ranges = "{}";
        for (Map.Entry<String, DateRange> entry : dateRanges.entrySet()) {
            ranges += new StringBuilder().append(" \"")
                    .append(entry.getKey())
                    .append("\": [\"")
                    .append(entry.getValue().getFrom())
                    .append("\", \"")
                    .append(entry.getValue().getTo())
                    .append("\"],")
                    .toString();
        }
        ranges += "}";

        init(getElement(), language, parentEl, startDate, endDate, minDate, maxDate, showDropdowns, showWeekNumbers, showISOWeekNumbers, singleDatePicker,
             timePicker, timePicker24Hour, timePickerIncrement, timePickerSeconds, dateLimit, autoApply, linkedCalendars, autoUpdateInput, opens, drops,
             buttonClasses, applyClass, cancelClass, ranges, alwaysShowCalendars, showCustomRangeLabels);
    }
}