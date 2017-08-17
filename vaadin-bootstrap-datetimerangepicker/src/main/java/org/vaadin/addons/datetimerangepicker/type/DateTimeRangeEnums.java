package org.vaadin.addons.datetimerangepicker.type;

public class DateTimeRangeEnums {

    public enum OPENS {
        RIGHT,
        LEFT,
        CENTER;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public enum DROPS {
        DOWN,
        UP;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
