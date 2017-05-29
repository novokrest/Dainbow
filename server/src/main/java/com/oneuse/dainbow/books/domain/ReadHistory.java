package com.oneuse.dainbow.books.domain;

import com.oneuse.core.TimeSpan;
import com.oneuse.dainbow.books.services.ReadActivityPerDayCalculator;

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
