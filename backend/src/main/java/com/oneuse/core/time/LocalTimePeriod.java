package com.oneuse.core.time;

import com.oneuse.core.Range;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class LocalTimePeriod extends Range<LocalTime> {
    public static LocalTimePeriod point(LocalTime point) {
        return new LocalTimePeriod(point, point);
    }

    public static LocalTimePeriod create(LocalTime begin, LocalTime end) {
        return new LocalTimePeriod(begin, end);
    }

    private LocalTimePeriod(LocalTime begin, LocalTime end) {
        super(begin, end);
    }

    public int hours() {
        return (int) TimeUnit.SECONDS.toHours(diffInSeconds());
    }

    public int minutes() {
        return (int) (TimeUnit.SECONDS.toMinutes(diffInSeconds()) - TimeUnit.HOURS.toMinutes(hours()));
    }

    public int seconds() {
        return (int) (diffInSeconds() - TimeUnit.MINUTES.toSeconds(minutes()) - TimeUnit.HOURS.toSeconds(hours()));
    }

    public int totalSeconds() {
        return diffInSeconds();
    }

    private int diffInSeconds() {
        return getEnd().toSecondOfDay() - getBegin().toSecondOfDay();
    }
}
