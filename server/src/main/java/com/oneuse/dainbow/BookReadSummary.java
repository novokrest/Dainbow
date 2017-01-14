package com.oneuse.dainbow;

import com.oneuse.core.TimeSpan;
import com.oneuse.dainbow.book.ReadHistory;


public class BookReadSummary {
    private final int readPagesCount;
    private final TimeSpan totalReadTime;

    public static BookReadSummary createFrom(ReadHistory readHistory) {
        int readPagesCount = readHistory.calculateTotalReadPages();
        TimeSpan totalReadTime = readHistory.calculateTotalReadTime();
        return new BookReadSummary(readPagesCount, totalReadTime);
    }

    public BookReadSummary(int readPagesCount, TimeSpan totalReadTime) {
        this.readPagesCount = readPagesCount;
        this.totalReadTime = totalReadTime;
    }

    public int getReadPagesCount() {
        return readPagesCount;
    }

    public TimeSpan getTotalReadTime() {
        return totalReadTime;
    }
}
