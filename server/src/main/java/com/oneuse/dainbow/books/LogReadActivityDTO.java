package com.oneuse.dainbow.books;

import java.time.LocalDate;
import java.time.LocalTime;

public interface LogReadActivityDTO {
    LocalDate getDay();
    LocalTime getBeginTime();
    LocalTime getEndTime();
    BookPages getPages();
}
