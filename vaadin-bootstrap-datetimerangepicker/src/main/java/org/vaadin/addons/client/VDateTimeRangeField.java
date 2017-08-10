package org.vaadin.addons.client;

import com.google.gwt.core.client.JsDate;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBoxBase;

// Extend any GWT Widget
public class VDateTimeRangeField extends TextBoxBase {

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
        init(getElement(), "de", "", "", "", "", false, false, false, false, false, false, timePickerIncrement, false, false, false, false, "rights", "down");
    }

    public void setUpdateValueHandler(final DateRangeFieldClientUpdateValueHandler updateValueHandler) {
        this.updateValueHandler = updateValueHandler;
    }

    private native void update_locale(String language) /*-{
                                                       alert("before7 update_locale: " + language )

                                                       // $wnd.moment.locale(language);

                                                       alert("after update_locale")
                                                       }-*/;

    private native void init(Element node, String language, String startDate, String endDate, String minDate, String maxDate, boolean showDropdowns,
            boolean showWeekNumbers, boolean showISOWeekNumbers, boolean singleDatePicker, boolean timePicker, boolean timePicker24Hour,
            int timePickerIncrement, boolean timePickerSeconds, boolean autoApply, boolean linkedCalendars, boolean autoUpdateInput, String opens,
            String drops) /*-{
                          var _this = this;
                          
                          alert("init language: " + language)
                          
                          $wnd.moment.locale(language);
                          
                          alert("init autoApply: " + autoApply)
                          alert("init startDate: " + startDate)
                          alert("init endDate: " + endDate)
                          
                          $wnd.$(node).daterangepicker({
                          showDropdowns: showDropdowns,
                          showWeekNumbers: showWeekNumbers,
                          showISOWeekNumbers: showISOWeekNumbers,
                          singleDatePicker: singleDatePicker,
                          timePicker: timePicker,
                          timePicker24Hour: timePicker24Hour,
                          timePickerSeconds: timePickerSeconds,
                          autoApply: autoApply,
                          linkedCalendars: linkedCalendars,
                          autoUpdateInput: autoUpdateInput,
                          opens: opens,
                          drops: drops,
                          startDate: startDate,
                          endDate: endDate,
                          minDate: minDate,
                          maxDate: maxDate
                          },
                          function(start, end, label) {
                          _this.@org.vaadin.addons.client.VDateTimeRangeField::onUpdateValue(Lcom/google/gwt/core/client/JsDate;Lcom/google/gwt/core/client/JsDate;)(start.toDate(),end.toDate());
                          });
                          
                          $wnd.$(node).on('apply.daterangepicker', function(ev, picker) {
                          $wnd.$(this).val(picker.startDate.format('MM/DD/YYYY') + ' - ' + picker.endDate.format('MM/DD/YYYY'));
                          });
                          }-*/;

    private native void init2(Element node, String applyLabel, String cancelLabel) /*-{
                                                                                   alert("init2 applyLabel: " + applyLabel)

                                                                                   $wnd.$(node).daterangepicker({
                                                                                   locale: {
                                                                                   applyLabel: applyLabel,
                                                                                   cancelLabel: cancelLabel }

                                                                                   });
                                                                                   }-*/;

    private void onUpdateValue(final JsDate start, final JsDate end) {
        this.updateValueHandler.onUpdateValue(start, end);
    }

    public static interface DateRangeFieldClientUpdateValueHandler extends EventHandler {
        void onUpdateValue(JsDate start, JsDate end);
    }

    public void setAutoApply(final boolean autoApply) {
        Window.alert("Window.alert");
        // setAutoApplyKGW(getElement(), autoApply);
    }

    public void refresh(final String language, final String applyLabel, final String cancelLabel, final String startDate, final String endDate,
            final String minDate, final String maxDate, final boolean showDropdowns, final boolean showWeekNumbers, final boolean showISOWeekNumbers,
            final boolean singleDatePicker, final boolean timePicker, final boolean timePicker24Hour, final int timePickerIncrement,
            final boolean timePickerSeconds, final boolean autoApply, final boolean linkedCalendars, final boolean autoUpdateInput, final String opens,
            final String drops) {
        init(getElement(), language, startDate, endDate, minDate, maxDate, showDropdowns, showWeekNumbers, showISOWeekNumbers, singleDatePicker, timePicker,
             timePicker24Hour, timePickerIncrement, timePickerSeconds, autoApply, linkedCalendars, autoUpdateInput, opens, drops);
        // init2(getElement(), applyLabel, cancelLabel);
    }

    private native void testKGW(Element node, boolean abc) /*-{
                                                                        alert("testKGW : abc" + abc)

                                                                        }-*/;

    // private native void setAutoApply(Element node, boolean autoApply) /*-{
    // $wnd.$(node).data('daterangepicker').autoApply = autoApply;
    // }-*/;

}