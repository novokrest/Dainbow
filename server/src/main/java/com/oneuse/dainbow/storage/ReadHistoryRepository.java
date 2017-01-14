package com.oneuse.dainbow.storage;

import com.oneuse.dainbow.ReadActivity;
import com.oneuse.dainbow.book.ReadHistory;

public interface ReadHistoryRepository {
    ReadHistory findReadHistory(long bookId);
    void logReadActivity(long bookId, ReadActivity readActivity);
}
