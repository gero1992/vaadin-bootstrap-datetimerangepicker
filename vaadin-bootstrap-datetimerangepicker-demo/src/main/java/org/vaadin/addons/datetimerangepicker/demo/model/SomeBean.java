package org.vaadin.addons.datetimerangepicker.demo.model;

import java.io.Serializable;

import org.vaadin.addons.datetimerangepicker.type.DateTimeRange;

public class SomeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private DateTimeRange dateRange = new DateTimeRange();

    public DateTimeRange getDateRange() {
        return this.dateRange;
    }

    public void setDateRange(final DateTimeRange dateRange) {
        this.dateRange = dateRange;
    }

}
