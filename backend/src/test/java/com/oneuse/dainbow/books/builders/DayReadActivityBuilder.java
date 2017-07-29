package com.oneuse.dainbow.books.builders;

import com.oneuse.dainbow.books.domain.BookPages;
import com.oneuse.dainbow.books.services.BookPagesParser;
import com.oneuse.dainbow.books.domain.DayReadActivity;

import java.time.LocalDate;

public class DayReadActivityBuilder {
    private LocalDate day;
    private BookPages bookPages;
    private int totalReadTimeSec;

    public DayReadActivityBuilder setDay(String day) {
        this.day = LocalDate.parse(day);
        return this;
    }

    public DayReadActivityBuilder setBookPages(String bookPages) {
        this.bookPages = BookPagesParser.parse(bookPages);
        return this;
    }

    public DayReadActivityBuilder setTotalReadTime(int totalReadTimeSec) {
        this.totalReadTimeSec = totalReadTimeSec;
        return this;
    }

    public DayReadActivity build() {
        return new DayReadActivity(day, bookPages, totalReadTimeSec);
    }
}
