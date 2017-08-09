package org.vaadin.addons.client;

import java.util.Date;

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

        init(getElement(), false, false, false, false, false, false, timePickerIncrement, false, false, false, false, "rights", "down");
    }

    public void setUpdateValueHandler(final DateRangeFieldClientUpdateValueHandler updateValueHandler) {
        this.updateValueHandler = updateValueHandler;
    }

    private native void init(Element node, boolean showDropdowns, boolean showWeekNumbers, boolean showISOWeekNumbers, boolean singleDatePicker,
            boolean timePicker, boolean timePicker24Hour, int timePickerIncrement, boolean timePickerSeconds, boolean autoApply, boolean linkedCalendars,
            boolean autoUpdateInput, String opens, String drops) /*-{
                                                                 var _this = this;

                                                                 alert("init autoApply: " + autoApply)

                                                                 $wnd.$(node).daterangepicker({
                                                                 showDropdowns: showDropdowns,
                                                                 showWeekNumbers: showWeekNumbers,
                                                                 showISOWeekNumbers: showISOWeekNumbers,
                                                                 singleDatePicker: singleDatePicker,
                                                                 timePicker: timePicker,
                                                                 timePicker24Hour: timePicker24Hour,
                                                                 timePickerSeconds: timePickerSeconds,
                                                                 autoApply: autoApply,
                                                                 locale: 'ru',
                                                                 linkedCalendars: linkedCalendars,
                                                                 autoUpdateInput: autoUpdateInput,
                                                                 opens: opens,
                                                                 drops: drops
                                                                 },
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

    public void setAutoApply(final boolean autoApply) {
        Window.alert("Window.alert");
        // setAutoApplyKGW(getElement(), autoApply);
    }

    public void refresh(final Date startDate, final Date endDate, final boolean showDropdowns, final boolean showWeekNumbers, final boolean showISOWeekNumbers,
            final boolean singleDatePicker, final boolean timePicker, final boolean timePicker24Hour, final int timePickerIncrement,
            final boolean timePickerSeconds, final boolean autoApply, final boolean linkedCalendars, final boolean autoUpdateInput, final String opens,
            final String drops) {
        init(getElement(), showDropdowns, showWeekNumbers, showISOWeekNumbers, singleDatePicker, timePicker, timePicker24Hour, timePickerIncrement,
             timePickerSeconds, autoApply, linkedCalendars, autoUpdateInput, opens, drops);
    }

    private native void init2(Element node, boolean autoApply) /*-{
                                                                         self.alert("init2 autoApply: " + autoApply)
                                                               
                                                                         }-*/;

    // private native void setAutoApply(Element node, boolean autoApply) /*-{
    // $wnd.$(node).data('daterangepicker').autoApply = autoApply;
    // }-*/;

}