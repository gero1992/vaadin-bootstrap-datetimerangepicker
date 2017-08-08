package org.vaadin.addons.demo;

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
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

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

    // UI Components

    // Initialize our new UI component
    private DateTimeRangeField dateRangeField;

    private CheckBox checkTimePicker;
    private CheckBox checkSingleDatePicker;
    private CheckBox checkAutoApply;
    private CheckBox checkLinkedCalendars;

    private Button btnRefreshUI;

    @Override
    protected void init(final VaadinRequest request) {

        // Initialize our new UI component
        this.dateRangeField = new DateTimeRangeField();

        final BeanFieldGroup<SomeBean> fieldGroup = new BeanFieldGroup<>(SomeBean.class);
        fieldGroup.setBuffered(false);
        fieldGroup.setItemDataSource(new SomeBean());
        fieldGroup.bind(this.dateRangeField, "dateRange");

        // Checkbox timePicker
        this.checkTimePicker = new CheckBox("timePicker");
        this.checkTimePicker.setValue(false);

        // Checkbox singleDatePicker
        this.checkSingleDatePicker = new CheckBox("singleDatePicker");
        this.checkSingleDatePicker.setValue(false);

        // Checkbox autoApply
        this.checkAutoApply = new CheckBox("autoApply");
        this.checkAutoApply.setValue(false);

        // Checkbox linkedCalendars
        this.checkLinkedCalendars = new CheckBox("linkedCalendars");
        this.checkLinkedCalendars.setValue(false);

        // Button buildUI

        this.btnRefreshUI = new Button("refreshUI");
        this.btnRefreshUI.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                DemoUI.this.dateRangeField.timePicker(DemoUI.this.checkTimePicker.getValue())
                        .singleDatePicker(DemoUI.this.checkSingleDatePicker.getValue())
                        .autoApply(DemoUI.this.checkAutoApply.getValue())
                        .linkedCalendars(DemoUI.this.checkLinkedCalendars.getValue())
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

        elementsLayout.addComponent(this.checkSingleDatePicker);
        elementsLayout.setComponentAlignment(this.checkSingleDatePicker, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.checkTimePicker);
        elementsLayout.setComponentAlignment(this.checkTimePicker, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.checkAutoApply);
        elementsLayout.setComponentAlignment(this.checkAutoApply, Alignment.MIDDLE_CENTER);

        elementsLayout.addComponent(this.checkLinkedCalendars);
        elementsLayout.setComponentAlignment(this.checkLinkedCalendars, Alignment.MIDDLE_CENTER);

        // ---

        elementsLayout.addComponent(this.btnRefreshUI);
        elementsLayout.setComponentAlignment(this.btnRefreshUI, Alignment.MIDDLE_CENTER);

        //
        // DateRangeField
        //

        elementsLayout.addComponent(this.dateRangeField);
        elementsLayout.setComponentAlignment(this.dateRangeField, Alignment.MIDDLE_CENTER);
        elementsLayout.addComponent(button);
        elementsLayout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);

        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.addComponent(elementsLayout);
        layout.setComponentAlignment(elementsLayout, Alignment.MIDDLE_CENTER);

        setContent(layout);
    }
}
