package com.oneuse.dainbow.books.storage;

import com.oneuse.dainbow.books.domain.ReadActivity;
import com.oneuse.dainbow.books.domain.ReadHistory;

public interface ReadHistoryRepository {
    ReadHistory findReadHistory(long bookId);
    void logReadActivity(long bookId, ReadActivity readActivity);
}
