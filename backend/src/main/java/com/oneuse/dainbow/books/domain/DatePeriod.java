package com.oneuse.dainbow.books.domain;

import com.oneuse.core.Range;

import java.util.Date;

public class DatePeriod extends Range<Date> {
    public DatePeriod(Date begin, Date end) {
        super(begin, end);
    }
}
