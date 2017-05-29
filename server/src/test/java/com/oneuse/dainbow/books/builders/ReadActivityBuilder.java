package com.oneuse.dainbow.books.builders;

import com.oneuse.core.time.DayPeriod;
import com.oneuse.dainbow.books.domain.BookPages;
import com.oneuse.dainbow.books.domain.BookPagesImpl;
import com.oneuse.dainbow.books.domain.ReadActivity;
import com.oneuse.dainbow.books.extensions.BookPagesEx;
import com.oneuse.dainbow.books.services.BookPagesParser;

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
