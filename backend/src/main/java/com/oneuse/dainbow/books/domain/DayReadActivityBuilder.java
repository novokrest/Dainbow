package com.oneuse.dainbow.books.domain;

import com.oneuse.dainbow.books.extensions.BookPagesEx;

import java.time.LocalDate;

public class DayReadActivityBuilder {
    private final LocalDate day;
    private final BookPages bookPages;
    private int totalReadTimeInSeconds;

    public DayReadActivityBuilder(LocalDate day) {
        this.day = day;
        this.bookPages = new BookPagesImpl();
    }

    public void addPages(BookPages pages) {
        BookPagesEx.add(this.bookPages, pages);
    }

    public void addTime(int seconds) {
        this.totalReadTimeInSeconds += seconds;
    }

    public DayReadActivity build() {
        return new DayReadActivity(day, bookPages, totalReadTimeInSeconds);
    }
}
