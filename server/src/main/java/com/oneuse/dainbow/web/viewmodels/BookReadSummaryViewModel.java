package com.oneuse.dainbow.web.viewmodels;

import com.oneuse.core.TimeSpan;
import com.oneuse.dainbow.BookReadSummary;
import com.oneuse.dainbow.BookService;
import com.oneuse.dainbow.book.ReadHistory;
import com.oneuse.dainbow.storage.ReadHistoryRepository;

public class BookReadSummaryViewModel {
    private int totalReadPages;
    private TimeSpan totalReadTime;

    public static BookReadSummaryViewModel create(ReadHistory readHistory) {
        BookReadSummary readSummary = BookReadSummary.createFrom(readHistory);
        BookReadSummaryViewModel viewModel = new BookReadSummaryViewModel();
        viewModel.setTotalReadPages(readSummary.getReadPagesCount());
        viewModel.setTotalReadTime(readSummary.getTotalReadTime());
        return viewModel;
    }

    public int getTotalReadPages() {
        return totalReadPages;
    }

    public void setTotalReadPages(int totalReadPages) {
        this.totalReadPages = totalReadPages;
    }

    public TimeSpan getTotalReadTime() {
        return totalReadTime;
    }

    public void setTotalReadTime(TimeSpan totalReadTime) {
        this.totalReadTime = totalReadTime;
    }
}
