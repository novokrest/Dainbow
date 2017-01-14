package com.oneuse.core;

import java.util.concurrent.TimeUnit;

public class TimeSpan {
    private final long seconds;

    public TimeSpan(long seconds) {
        this.seconds = seconds;
    }

    public long hours() {
        return TimeUnit.SECONDS.toHours(seconds);
    }

    public long minutes() {
        return TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.HOURS.toMinutes(hours());
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hours(), minutes());
    }
}
