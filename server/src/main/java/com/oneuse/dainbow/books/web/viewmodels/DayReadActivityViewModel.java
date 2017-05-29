package com.oneuse.dainbow.books.web.viewmodels;

import com.oneuse.core.TimeSpan;
import com.oneuse.dainbow.books.BookPagesEx;
import com.oneuse.dainbow.books.DayReadActivity;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class DayReadActivityViewModel {
    private static final String DATETIME_FORMAT_PATTERN = "dd-MM-yyyy";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATETIME_FORMAT_PATTERN);

    private final String day;
    private final String pages;
    private final String totalReadTime;

    public static DayReadActivityViewModel createFrom(DayReadActivity dayReadActivity) {
        String day = dayReadActivity.getDay().format(DateTimeFormatter.ISO_DATE);
        String pages = BookPagesEx.toString(dayReadActivity.getBookPages());
        String totalReadTime = new TimeSpan(dayReadActivity.getTotalReadTimeInSeconds()).toString();

        return new DayReadActivityViewModel(day, pages, totalReadTime);
    }

    private DayReadActivityViewModel(String day, String pages, String totalReadTime) {
        this.day = day;
        this.pages = pages;
        this.totalReadTime = totalReadTime;
    }

    public String getDay() {
        return day;
    }

    public String getPages() {
        return pages;
    }

    public String getTotalReadTime() {
        return totalReadTime;
    }
}
