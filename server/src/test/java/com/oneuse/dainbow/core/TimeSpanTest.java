package com.oneuse.dainbow.core;

import com.oneuse.core.TimeSpan;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeSpanTest {
    @Test
    public void testCommon() {
        int hours = 1;
        int minutes = 13;
        int seconds = 15;

        TimeSpan timeSpan = new TimeSpan(TimeUnit.HOURS.toSeconds(hours) +
                                         TimeUnit.MINUTES.toSeconds(minutes) +
                                         seconds);

        Assert.assertEquals(hours, timeSpan.hours());
        Assert.assertEquals(minutes, timeSpan.minutes());
        Assert.assertEquals("01:13", timeSpan.toString());
    }
}
