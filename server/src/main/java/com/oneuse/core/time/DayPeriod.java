package com.oneuse.core.time;

import java.time.LocalDate;
import java.time.LocalTime;

public class DayPeriod {
    private final LocalDate day;
    private final LocalTimePeriod timePeriod;

    public DayPeriod(LocalDate day, LocalTime from, LocalTime to) {
        this(day, LocalTimePeriod.create(from, to));
    }

    public DayPeriod(LocalDate day, LocalTimePeriod timePeriod) {
        this.day = day;
        this.timePeriod = timePeriod;
    }

    public LocalDate day() {
        return day;
    }

    public LocalTimePeriod timePeriod() {
        return timePeriod;
    }

    public int hours() {
        return timePeriod.hours();
    }

    public int minutes() {
        return timePeriod.minutes();
    }

    public int seconds() {
        return timePeriod.seconds();
    }

    public int totalSeconds() {
        return timePeriod.totalSeconds();
    }
}
