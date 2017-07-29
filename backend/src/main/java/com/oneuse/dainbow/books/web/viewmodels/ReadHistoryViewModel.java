package com.oneuse.dainbow.books.web.viewmodels;

import com.oneuse.dainbow.books.domain.ReadHistory;

import java.util.List;
import java.util.stream.Collectors;

public class ReadHistoryViewModel {
    private final List<DayReadActivityViewModel> pages;

    public static ReadHistoryViewModel create(ReadHistory readHistory) {
        List<DayReadActivityViewModel> pagesViewModels = readHistory.getReadActivities()
                                                         .stream()
                                                         .map(DayReadActivityViewModel::createFrom)
                                                         .collect(Collectors.toList());

        return new ReadHistoryViewModel(pagesViewModels);
    }

    private ReadHistoryViewModel(List<DayReadActivityViewModel> pages) {
        this.pages = pages;
    }

    public List<DayReadActivityViewModel> getPages() {
        return pages;
    }
}
