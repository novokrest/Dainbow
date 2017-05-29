package com.oneuse.dainbow.books.storage;

import com.oneuse.dainbow.books.DatePeriod;
import java.util.Date;

public class LoggedPagesEntry {
    private final int beginPage;
    private final int endPage;
    private final DatePeriod period;

    public LoggedPagesEntry(int beginPage, int endPage, Date beginDate, Date endDate) {
        this.beginPage = beginPage;
        this.endPage = endPage;
        this.period = new DatePeriod(beginDate, endDate);
    }

    public int getBeginPage() {
        return beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public DatePeriod getPeriod() {
        return period;
    }
}
