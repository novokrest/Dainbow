package com.oneuse.dainbow;

import java.time.LocalDate;

public class DayReadActivity {
    private final LocalDate day;
    private final BookPages bookPages;
    private final int totalReadTimeSec;

    public DayReadActivity(LocalDate day, BookPages bookPages, int totalReadTimeSec) {
        this.day = day;
        this.bookPages = bookPages;
        this.totalReadTimeSec = totalReadTimeSec;
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
        return totalReadTimeSec;
    }
}
