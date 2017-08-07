package org.vaadin.addons.client;

import com.google.gwt.core.client.JsDate;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBoxBase;

// Extend any GWT Widget
public class VDateTimeRangeField extends TextBoxBase {

    public final boolean autoApply = true;

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
        init(getElement());
    }

    public void setUpdateValueHandler(final DateRangeFieldClientUpdateValueHandler updateValueHandler) {
        this.updateValueHandler = updateValueHandler;
    }

    private native void init(Element node) /*-{
                                           var _this = this;

                                           self.alert(_this)


                                           $wnd.$(node).daterangepicker({
                                           autoUpdateInput: false,
                                           autoApply: this.autoApply
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
        setAutoApplyKGW(getElement(), autoApply);
    }

    private native void setAutoApplyKGW(Element node, boolean autoApply) /*-{
                                                                         self.alert('Hallo Soeren')
                                                                         $wnd.$(node).data('daterangepicker').setStartDate('08/01/2017');
                                                                         self.alert('Hallo Soeren2')
                                                                         }-*/;

    private native void setAutoApply(Element node, boolean autoApply) /*-{
                                                                      $wnd.$(node).data('daterangepicker').autoApply = autoApply;
                                                                      }-*/;

}