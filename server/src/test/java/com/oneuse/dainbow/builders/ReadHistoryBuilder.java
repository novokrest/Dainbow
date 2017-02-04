package com.oneuse.dainbow.builders;

import com.oneuse.core.Verifiers;
import com.oneuse.dainbow.ReadActivity;
import com.oneuse.dainbow.book.ReadHistory;

import java.util.ArrayList;
import java.util.List;

public class ReadHistoryBuilder {
    private final List<ReadActivity> activities = new ArrayList<>();
    private ReadActivityBuilder currentBuilder;

    public ReadHistoryBuilder addReadActivity() {
        buildCurrentReadActivity();
        currentBuilder = new ReadActivityBuilder();
        return this;
    }

    public ReadHistoryBuilder addPages(String pages) {
        Verifiers.verify(currentBuilder != null);

        currentBuilder.addPages(pages);
        return this;
    }

    public ReadHistoryBuilder setDayPeriod(String day, String fromTime, String toTime) {
        currentBuilder.setDayPeriod(day, fromTime, toTime);
        return this;
    }

    public ReadHistory build() {
        buildCurrentReadActivity();
        return new ReadHistory(activities);
    }

    private void buildCurrentReadActivity() {
        if (currentBuilder != null) {
            activities.add(currentBuilder.build());
        }
    }
}
