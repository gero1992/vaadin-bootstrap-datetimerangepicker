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
    private DateTimeRangeField dateRangeField = new DateTimeRangeField.DateTimeRangeFieldBuilder().build();

    private CheckBox checkSingleDatePicker = null;
    private CheckBox checkAutoApply = null;
    private Button btnBuildUI = null;

    @Override
    protected void init(final VaadinRequest request) {

        final BeanFieldGroup<SomeBean> fieldGroup = new BeanFieldGroup<>(SomeBean.class);
        fieldGroup.setBuffered(false);
        fieldGroup.setItemDataSource(new SomeBean());
        fieldGroup.bind(this.dateRangeField, "dateRange");

        // Checkbox singleDatePicker

        this.checkSingleDatePicker = new CheckBox("singleDatePicker");
        this.checkSingleDatePicker.setValue(false);
        // this.checkSingleDatePicker.addValueChangeListener(new ValueChangeListener() {
        //
        // @Override
        // public void valueChange(final ValueChangeEvent event) {
        // checkSingleDatePickerthis.dateRangeField.setAutoApply(checkAutoApply.getValue());
        // }
        // });

        // Checkbox autoApply

        this.checkAutoApply = new CheckBox("autoApply");
        this.checkAutoApply.setValue(false);
        // this.checkAutoApply.addValueChangeListener(new ValueChangeListener() {
        //
        // @Override
        // public void valueChange(final ValueChangeEvent event) {
        // setAutodateRangeField.setAutoApply(checkAutoApply.getValue());
        // }
        // });

        // Button buildUI

        this.btnBuildUI = new Button("buildUI");
        this.btnBuildUI.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                DemoUI.this.dateRangeField = new DateTimeRangeField.DateTimeRangeFieldBuilder().singleDtePicker(DemoUI.this.checkSingleDatePicker.getValue())
                        .autoApply(DemoUI.this.checkAutoApply.getValue())
                        .build();
            }
        });

        // Button show

        final Button button = new Button("show");
        button.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                fieldGroup.getItemDataSource()
                        .getBean()
                        .getDateRange()
                        .toString();
            }
        });

        final VerticalLayout elementsLayout = new VerticalLayout();
        elementsLayout.setSpacing(true);

        elementsLayout.addComponent(this.checkSingleDatePicker);
        elementsLayout.setComponentAlignment(this.checkSingleDatePicker, Alignment.MIDDLE_CENTER);
        elementsLayout.addComponent(this.checkAutoApply);
        elementsLayout.setComponentAlignment(this.checkAutoApply, Alignment.MIDDLE_CENTER);
        elementsLayout.addComponent(this.checkAutoApply);
        elementsLayout.setComponentAlignment(this.checkAutoApply, Alignment.MIDDLE_CENTER);
        elementsLayout.addComponent(this.btnBuildUI);
        elementsLayout.setComponentAlignment(this.btnBuildUI, Alignment.MIDDLE_CENTER);

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
