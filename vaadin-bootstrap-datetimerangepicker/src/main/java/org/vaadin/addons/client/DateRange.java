package org.vaadin.addons.client;

/**
 * Contains single range of DateTimeRangePicker.
 */
public class DateRange {
    private String from;
    private String to;

    public DateRange() {
    }

    public DateRange(final String rangeFrom, final String rangeTo) {
        this.from = rangeFrom;
        this.to = rangeTo;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }
}
