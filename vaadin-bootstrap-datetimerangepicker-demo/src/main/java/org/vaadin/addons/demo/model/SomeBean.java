package org.vaadin.addons.demo.model;

import org.vaadin.addons.type.DateTimeRange;

public class SomeBean {

    private DateTimeRange dateRange = new DateTimeRange();

    public DateTimeRange getDateRange() {
	return this.dateRange;
    }

    public void setDateRange(final DateTimeRange dateRange) {
	this.dateRange = dateRange;
    }

}
