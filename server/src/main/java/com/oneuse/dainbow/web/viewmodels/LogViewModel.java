package com.oneuse.dainbow.web.viewmodels;

import com.oneuse.dainbow.BookPages;
import com.oneuse.dainbow.LogReadActivityDTO;
import com.oneuse.dainbow.ui.DateTimeFormatEx;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class LogViewModel implements LogReadActivityDTO {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate day;

    @DateTimeFormat(pattern = DateTimeFormatEx.HoursMinutes)
    private LocalTime beginTime;

    @DateTimeFormat(pattern = DateTimeFormatEx.HoursMinutes)
    private LocalTime endTime;

    private BookPages pages;

    public static LogViewModel forToday() {
        LogViewModel logViewModel = new LogViewModel();
        logViewModel.setDay(LocalDate.now());
        logViewModel.setBeginTime(LocalTime.now());
        logViewModel.setEndTime(LocalTime.now());
        return logViewModel;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public BookPages getPages() {
        return pages;
    }

    public void setPages(BookPages pages) {
        this.pages = pages;
    }
}
