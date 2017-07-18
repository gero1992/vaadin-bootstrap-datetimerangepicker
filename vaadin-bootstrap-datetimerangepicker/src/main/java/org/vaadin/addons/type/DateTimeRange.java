package org.vaadin.addons.type;

import java.util.Date;

public class DateTimeRange {

	private Date from;
	private Date to;

	public DateTimeRange() {
		this(null, null);
	}

	public DateTimeRange(Date date) {
		this(date, date);
	}

	public DateTimeRange(Date from, Date to) {
		this.from = from;
		this.to = to;
	}

	public Date getFrom() {
		return this.from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return this.to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "from: " + (this.from == null ? "(empty)" : this.from.toString()) + ", to: "
				+ (this.to == null ? "(empty)" : this.to.toString());
	}
}
