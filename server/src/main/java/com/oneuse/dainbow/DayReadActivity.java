package com.oneuse.dainbow;

import java.time.LocalDate;

public class DayReadActivity {
    private final LocalDate day;
    private final BookPages bookPages;
    private final int totalReadTime;

    public DayReadActivity(LocalDate day, BookPages bookPages, int totalReadTime) {
        this.day = day;
        this.bookPages = bookPages;
        this.totalReadTime = totalReadTime;
    }

    public LocalDate getDay() {
        return day;
    }

    public int getReadPagesCount() {
        return bookPages.getPagesCount();
    }

    public BookPages getBookPages() {
        return bookPages;
    }

    public int getTotalReadTimeInSeconds() {
        return totalReadTime;
    }
}
