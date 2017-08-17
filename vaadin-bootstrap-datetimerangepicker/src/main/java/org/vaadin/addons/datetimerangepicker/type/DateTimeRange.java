package org.vaadin.addons.datetimerangepicker.type;

import java.io.Serializable;
import java.util.Date;

public class DateTimeRange implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date from;
    private Date to;

    public DateTimeRange() {
        this(null, null);
    }

    public DateTimeRange(final Date date) {
        this(date, date);
    }

    public DateTimeRange(final Date from, final Date to) {
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return this.from;
    }

    public void setFrom(final Date from) {
        this.from = from;
    }

    public Date getTo() {
        return this.to;
    }

    public void setTo(final Date to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "from: " + (this.from == null ? "(empty)" : this.from.toString()) + ", to: " + (this.to == null ? "(empty)" : this.to.toString());
    }
}
