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
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("MyComponent Add-on Demo")
@Widgetset("org.vaadin.addons.demo.DemoWidgetSet")
@SuppressWarnings("serial")
public class DemoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {

		// Initialize our new UI component
		final DateTimeRangeField dateRangeField = new DateTimeRangeField();

		BeanFieldGroup<SomeBean> fieldGroup = new BeanFieldGroup<>(SomeBean.class);
		fieldGroup.setBuffered(false);
		fieldGroup.setItemDataSource(new SomeBean());
		fieldGroup.bind(dateRangeField, "dateRange");

		Button button = new Button("show");
		button.addClickListener(event -> Notification.show(fieldGroup.getItemDataSource()
			.getBean()
			.getDateRange()
			.toString()));

		final VerticalLayout elementsLayout = new VerticalLayout();
		elementsLayout.setSpacing(true);
		elementsLayout.addComponent(dateRangeField);
		elementsLayout.setComponentAlignment(dateRangeField, Alignment.MIDDLE_CENTER);
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
