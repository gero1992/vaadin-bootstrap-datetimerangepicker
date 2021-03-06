package org.vaadin.addons.datetimerangepicker.client;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.JsDate;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.TextBoxBase;

// Extend any GWT Widget
public class VDateTimeRangeField extends TextBoxBase {

    private static final String EMPTY_STRING = "";
    private static final String CLASSNAME = "bp-datetimerangepicker";

    private DateRangeFieldClientUpdateValueHandler updateValueHandler;
    private DateRangeFieldClientResetValueHandler resetValueHandler;

    private final com.google.gwt.dom.client.Element inputText;

    /*
     * Constructor. Ensures that needed html templates are added and injects a <database-visualizer> element to the page.
     */
    public VDateTimeRangeField() {
        this(DOM.createDiv());
    }

    protected VDateTimeRangeField(final Element node) {
        super(node);
        this.inputText = DOM.createInputText();
        this.inputText.addClassName("v-textfield");
        this.inputText.addClassName(VDateTimeRangeField.CLASSNAME);
        getElement().appendChild(this.inputText);
        this.inputText.getStyle()
            .setProperty("width", "100%");
        // add Date Select Icon
        final com.google.gwt.user.client.Element i = DOM.createElement("i");
        i.addClassName("glyphicon glyphicon-calendar fa fa-calendar");
        i.addClassName(VDateTimeRangeField.CLASSNAME + "-calendarbutton");
        getElement().appendChild(i);
        // add Date Select Icon
        final com.google.gwt.user.client.Element resetbutton = DOM.createElement("i");
        resetbutton.addClassName(VDateTimeRangeField.CLASSNAME + "-resetbutton");
        getElement().appendChild(resetbutton);

        DOM.sinkEvents(resetbutton, Event.ONCLICK);

        Event.setEventListener(resetbutton, event -> {
            if (Event.ONCLICK == event.getTypeInt()) {
                this.resetValueHandler.onResetValue();
            }
        });

        setStylePrimaryName("bp-datetimerangepicker");
    }

    public void setElementUUID(final String elementUUID) {
        this.inputText.setId(elementUUID);
    }

    public void setUpdateValueHandler(final DateRangeFieldClientUpdateValueHandler updateValueHandler) {
        this.updateValueHandler = updateValueHandler;
    }

    public void setResetValueHandler(final DateRangeFieldClientResetValueHandler resetValueHandler) {
        this.resetValueHandler = resetValueHandler;
    }

    private native void init(Element node, String language, String parentEl, String startDate, String endDate, String minDate, String maxDate,
            boolean showDropdowns, boolean showWeekNumbers, boolean showISOWeekNumbers, boolean singleDatePicker, boolean timePicker, boolean timePicker24Hour,
            int timePickerIncrement, boolean timePickerSeconds, String dateLimit, boolean autoApply, boolean linkedCalendars, boolean autoUpdateInput,
            String opens, String drops, String buttonClasses, String applyClass, String cancelClass, String ranges, boolean alwaysShowCalendars,
            boolean showCustomRangeLabel, String applyLabel, String cancelLabel, String datePattern, boolean workable, String elementId) /*-{
                                                                                                                                         var _this = this;

                                                                                                                                         $wnd.moment.locale(language);

                                                                                                                                          configString = '{' +
                                                                                                                                         '"showDropdowns": ' + showDropdowns + ',' +
                                                                                                                                         '"showWeekNumbers": ' + showWeekNumbers + ',' +
                                                                                                                                         '"showISOWeekNumbers": ' + showISOWeekNumbers + ',' +
                                                                                                                                         '"singleDatePicker": ' + singleDatePicker + ',' +
                                                                                                                                         '"timePicker": ' + timePicker + ',' +
                                                                                                                                         '"timePicker24Hour": ' + timePicker24Hour + ',' +
                                                                                                                                         '"timePickerIncrement": ' + timePickerIncrement + ',' +
                                                                                                                                         '"timePickerSeconds": ' + timePickerSeconds + ',' +
                                                                                                                                         (typeof(dateLimit) === 'undefined' || dateLimit.length == 0 ? '' : '"dateLimit": ' + dateLimit + ',') +
                                                                                                                                         '"autoApply": ' + autoApply + ',' +
                                                                                                                                         '"linkedCalendars": ' + linkedCalendars + ',' +
                                                                                                                                         '"autoUpdateInput": ' + autoUpdateInput + ',' +
                                                                                                                                         '"opens": "' + opens + '",' +
                                                                                                                                         '"drops": "' + drops + '",' +
                                                                                                                                         '"parentEl": "' + parentEl + '",' +
                                                                                                                                         (typeof(startDate) === 'undefined' || startDate.length == 0 ? '' : '"startDate": "' + startDate + '",') +
                                                                                                                                         (typeof(endDate) === 'undefined' || endDate.length == 0 ? '' : '"endDate": "' + endDate + '",') +
                                                                                                                                         (typeof(minDate) === 'undefined' || minDate.length == 0 ? '' : '"minDate": "' + minDate + '",') +
                                                                                                                                         (typeof(maxDate) === 'undefined' || maxDate.length == 0 ? '' : '"maxDate": "' + maxDate + '",') +
                                                                                                                                         '"buttonClasses": "' + buttonClasses + '",' +
                                                                                                                                         '"applyClass": "' + applyClass + '",' +
                                                                                                                                         '"cancelClass": "' + cancelClass + '",' +
                                                                                                                                         (typeof(ranges) === 'undefined' || ranges.length == 0 ? '' : '"ranges": ' + ranges + ',') +
                                                                                                                                         '"alwaysShowCalendars": ' + alwaysShowCalendars + ',' +
                                                                                                                                         '"showCustomRangeLabel": ' + showCustomRangeLabel + ',' +
                                                                                                                                         '"locale": { "applyLabel": "' + applyLabel + '",' +
                                                                                                                                         '"cancelLabel": "' + cancelLabel + '"}' + ',' +
                                                                                                                                         '"format": "' + datePattern + '"' +
                                                                                                                                         '}';

                                                                                                                                         $wnd.console.log(configString);

                                                                                                                                         $wnd.$(node).daterangepicker(JSON.parse(configString),
                                                                                                                                         function(start, end, label) {
                                                                                                                                         _this.@org.vaadin.addons.datetimerangepicker.client.VDateTimeRangeField::onUpdateValue(Lcom/google/gwt/core/client/JsDate;Lcom/google/gwt/core/client/JsDate;)(start.toDate(),end.toDate());
                                                                                                                                         });


                                                                                                                                         $wnd.$(node).on('apply.daterangepicker', function(ev, picker) {
                                                                                                                                             $wnd.$(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
                                                                                                                                         });
                                                                                                                                         
                                                                                                                                         
                                                                                                                                         $doc.getElementById(elementId).disabled=!workable;

                                                                                                                                         }-*/;

    private void onUpdateValue(final JsDate start, final JsDate end) {
        this.updateValueHandler.onUpdateValue(start, end);
    }

    public static interface DateRangeFieldClientResetValueHandler extends EventHandler {
        void onResetValue();
    }

    public static interface DateRangeFieldClientUpdateValueHandler extends EventHandler {
        void onUpdateValue(JsDate start, JsDate end);
    }

    public void refresh(final String language, final String applyLabel, final String cancelLabel, final String parentEl, final String startDate,
            final String endDate, final String minDate, final String maxDate, final boolean showDropdowns, final boolean showWeekNumbers,
            final boolean showISOWeekNumbers, final boolean singleDatePicker, final boolean timePicker, final boolean timePicker24Hour,
            final int timePickerIncrement, final boolean timePickerSeconds, final String dateLimitSpanMoment, final int dateLimitSpanValue,
            final boolean autoApply, final boolean linkedCalendars, final boolean autoUpdateInput, final String opens, final String drops,
            final String buttonClasses, final String applyClass, final String cancelClass, final Map<String, List<String>> dateRanges,
            final boolean alwaysShowCalendars, final boolean showCustomRangeLabels, final String datePattern, final boolean workable) {

        // DateLimit Processing
        String dateLimit = "";
        if (dateLimitSpanMoment != null && !dateLimitSpanMoment.equals(VDateTimeRangeField.EMPTY_STRING)) {
            dateLimit = new StringBuilder().append("{ \"")
                .append(dateLimitSpanMoment)

                .append("\": ")
                .append(dateLimitSpanValue)
                .append(" }")
                .toString();
        }

        // Ranges Processing
        String ranges = "";
        if (dateRanges.size() > 0) {
            int elementCount = 0;
            ranges = "{";
            for (final Map.Entry<String, List<String>> entry : dateRanges.entrySet()) {
                elementCount++;
                ranges += new StringBuilder().append(" \"")
                    .append(entry.getKey())
                    .append("\": [\"")
                    .append(entry.getValue()
                        .get(0))
                    .append("\", \"")
                    .append(entry.getValue()
                        .get(1))
                    .append("\"]")
                    .toString();
                if (elementCount < dateRanges.size()) {
                    ranges += ",";
                }
            }
            ranges += "}";
        }
        if (!workable) {
            this.setEnabled(false);
        }
        else {
            this.setEnabled(true);
        }

        init(this.inputText, language, parentEl, startDate, endDate, minDate, maxDate, showDropdowns, showWeekNumbers, showISOWeekNumbers, singleDatePicker,
             timePicker, timePicker24Hour, timePickerIncrement, timePickerSeconds, dateLimit, autoApply, linkedCalendars, autoUpdateInput, opens, drops,
             buttonClasses, applyClass, cancelClass, ranges, alwaysShowCalendars, showCustomRangeLabels, applyLabel, cancelLabel, datePattern, workable,
             this.inputText.getId());
    }
}