package com.oneuse.dainbow.books;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadActivityPerDayCalculator {
    Map<LocalDate, DayReadActivityBuilder> dayReadActivityPerDay = new HashMap<>();

    public List<DayReadActivity> calculate(List<ReadActivity> readActivities) {
        readActivities.forEach(this::addReadActivity);
        return dayReadActivityPerDay.entrySet()
                                    .stream()
                                    .map(entry -> entry.getValue().build()).collect(Collectors.toList());
    }

    private void addReadActivity(ReadActivity readActivity) {
        addPages(readActivity.getPeriod().day(), readActivity.getBookPages());
        addTime(readActivity.getPeriod().day(), readActivity.getPeriod().totalSeconds());
    }

    private void addPages(LocalDate day, BookPages pages) {
        getDayReadActivityBuilder(day).addPages(pages);
    }

    private void addTime(LocalDate day, int time) {
        getDayReadActivityBuilder(day).addTime(time);
    }

    private DayReadActivityBuilder getDayReadActivityBuilder(LocalDate day) {
        if (!dayReadActivityPerDay.containsKey(day)) {
            dayReadActivityPerDay.put(day, new DayReadActivityBuilder(day));
        }
        return dayReadActivityPerDay.get(day);
    }
}
