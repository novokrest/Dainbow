package com.oneuse.dainbow;

import java.time.LocalDate;

public class DayReadActivityBuilder {
    private final LocalDate day;
    private final BookPages bookPages;
    private long totalReadTimeInSeconds;

    public DayReadActivityBuilder(LocalDate day) {
        this.day = day;
        this.bookPages = new BookPagesImpl();
    }

    public void addPages(BookPages pages) {
        BookPagesEx.add(this.bookPages, pages);
    }

    public void addTime(long seconds) {
        this.totalReadTimeInSeconds += seconds;
    }

    public DayReadActivity build() {
        return new DayReadActivity(day, bookPages, totalReadTimeInSeconds);
    }
}
