package com.oneuse.dainbow.books.dto;

import com.oneuse.dainbow.books.domain.BookPages;

import java.time.LocalDate;
import java.time.LocalTime;

public interface LogReadActivityDTO {
    LocalDate getDay();
    LocalTime getBeginTime();
    LocalTime getEndTime();
    BookPages getPages();
}
