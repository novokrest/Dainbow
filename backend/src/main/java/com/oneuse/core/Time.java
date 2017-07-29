package com.oneuse.core;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Time {
    private final Calendar calendar = Calendar.getInstance();

    public Time(Date date) {
        calendar.setTime(date);
    }

    public long hours() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public long minutes() {
        return calendar.get(Calendar.MINUTE);
    }

    public long seconds() {
        return calendar.get(Calendar.SECOND);
    }

    public long getMilliseconds() {
        return TimeUnit.HOURS.toMillis(hours())
                + TimeUnit.MINUTES.toMillis(minutes())
                + TimeUnit.SECONDS.toMillis(seconds());
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hours(), minutes());
    }
}