package com.oneuse.dainbow.core.time;

import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.oneuse.core.CollectionEx;
import com.oneuse.core.time.LocalTimePeriod;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class LocalTimePeriodTest {
    @Test
    public void testOnePointPeriodHasZeroHoursAndZeroMinutesAndZeroSeconds() {
        LocalTimePeriod point = LocalTimePeriod.point(LocalTime.now());
        Assert.assertEquals(point.hours(), 0);
        Assert.assertEquals(point.minutes(), 0);
        Assert.assertEquals(point.seconds(), 0);
    }

    @Test
    public void failIfBeginLessThanEnd() {
        try {
            LocalTimePeriod.create(LocalTime.now().plusSeconds(10), LocalTime.now());
        } catch (Exception e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void testNotZeroPeriods() {
        testPeriodHasSpecifiedValues("12:00:00", "12:00:00", 00, 00, 00);
        testPeriodHasSpecifiedValues("12:00:00", "13:00:00", 01, 00, 00);
        testPeriodHasSpecifiedValues("12:00:00", "12:11:00", 00, 11, 00);
        testPeriodHasSpecifiedValues("12:00:00", "12:00:31", 00, 00, 31);
    }

    @Test
    public void testGeneratedPeriods() {
        int[] hours = new int[] { 0, 1, 11, 23 };
        int[] minutes = new int[] { 0, 1, 12, 59 };
        int[] seconds = new int[] { 0, 1, 31, 59 };

        LocalTime begin = LocalTime.parse("00:00:00");

        Set<List<Integer>> diffs = Sets.cartesianProduct(
                CollectionEx.asSet(Ints.asList(hours)),
                CollectionEx.asSet(Ints.asList(minutes)),
                CollectionEx.asSet(Ints.asList(seconds))
        );

        for (List<Integer> periodValues : diffs) {
            int expectedHours = periodValues.get(0);
            int expectedMinutes = periodValues.get(1);
            int expectedSeconds = periodValues.get(2);

            LocalTime end = begin.plusHours(expectedHours)
                                 .plusMinutes(expectedMinutes)
                                 .plusSeconds(expectedSeconds);

            testPeriodHasSpecifiedValues(begin.toString(), end.toString(),
                                         expectedHours, expectedMinutes, expectedSeconds);
        }
    }

    private void testPeriodHasSpecifiedValues(String beginTime, String endTime,
                                              int expectedHours, int expectedMinutes, int expectedSeconds) {
        LocalTime begin = LocalTime.parse(beginTime);
        LocalTime end = LocalTime.parse(endTime);

        LocalTimePeriod period = LocalTimePeriod.create(begin, end);

        Assert.assertEquals(expectedHours, period.hours());
        Assert.assertEquals(expectedMinutes, period.minutes());
        Assert.assertEquals(expectedSeconds, period.seconds());
    }
}
