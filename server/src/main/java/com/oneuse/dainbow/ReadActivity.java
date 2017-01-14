package com.oneuse.dainbow;

import com.oneuse.core.time.DayPeriod;

public class ReadActivity {
    private final BookPages bookPages;
    private final DayPeriod period;

    public ReadActivity(BookPages bookPages, DayPeriod period) {
        this.bookPages = bookPages;
        this.period = period;
    }

    public BookPages getBookPages() {
        return bookPages;
    }

    public DayPeriod getPeriod() {
        return period;
    }
}
