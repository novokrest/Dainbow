package com.oneuse.dainbow.book;

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
}
