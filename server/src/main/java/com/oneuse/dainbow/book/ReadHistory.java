package com.oneuse.dainbow.book;

import com.oneuse.core.TimeSpan;
import com.oneuse.dainbow.DayReadActivity;
import com.oneuse.dainbow.ReadActivity;
import com.oneuse.dainbow.ReadActivityPerDayCalculator;

import java.util.List;

public class ReadHistory {
    private final List<DayReadActivity> readActivities;

    public ReadHistory(List<ReadActivity> readActivities) {
        ReadActivityPerDayCalculator calculator = new ReadActivityPerDayCalculator();
        this.readActivities = calculator.calculate(readActivities);
    }

    public List<DayReadActivity> getReadActivities() {
        return readActivities;
    }

    public int calculateTotalReadPages() {
        return getReadActivities().stream()
                                  .mapToInt(DayReadActivity::getReadPagesCount)
                                  .sum();
    }

    public TimeSpan calculateTotalReadTime() {
        return new TimeSpan(getReadActivities().stream()
                                               .mapToInt(DayReadActivity::getTotalReadTimeInSeconds)
                                               .sum());
    }
}
