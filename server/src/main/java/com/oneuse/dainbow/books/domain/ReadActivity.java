package com.oneuse.dainbow.books.domain;

import com.oneuse.core.time.DayPeriod;
import com.oneuse.dainbow.books.domain.BookPages;

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
