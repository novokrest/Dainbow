package com.oneuse.dainbow;

import java.time.LocalDate;

public class DayReadActivity {
    private final LocalDate day;
    private final BookPages bookPages;
    private final long totalReadTime;

    public DayReadActivity(LocalDate day, BookPages bookPages, long totalReadTime) {
        this.day = day;
        this.bookPages = bookPages;
        this.totalReadTime = totalReadTime;
    }

    public LocalDate getDay() {
        return day;
    }

    public BookPages getBookPages() {
        return bookPages;
    }

    public long getTotalReadTime() {
        return totalReadTime;
    }
}
