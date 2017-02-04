package com.oneuse.dainbow.builders;

import com.oneuse.core.time.DayPeriod;
import com.oneuse.dainbow.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReadActivityBuilder {
    private final BookPages bookPages = new BookPagesImpl();
    private DayPeriod dayPeriod;

    public ReadActivityBuilder addPages(String pages) {
        BookPagesEx.add(bookPages, BookPagesParser.parse(pages));
        return this;
    }

    public ReadActivityBuilder setDayPeriod(String day, String fromTime, String toTime) {
        this.dayPeriod = new DayPeriod(LocalDate.parse(day),
                                       LocalTime.parse(fromTime),
                                       LocalTime.parse(toTime));
        return this;
    }

    public ReadActivity build() {
        return new ReadActivity(bookPages, dayPeriod);
    }
}
