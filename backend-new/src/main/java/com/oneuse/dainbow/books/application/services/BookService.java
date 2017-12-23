package com.oneuse.dainbow.books.application.services;

import com.oneuse.dainbow.books.application.dao.BookDao;
import com.oneuse.dainbow.books.application.dao.BookHistoryDao;
import com.oneuse.dainbow.books.application.entity.BookEntity;
import com.oneuse.dainbow.books.application.entity.BookReadActivityEntity;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookService {

    private final BookDao bookDao;
    private final BookHistoryDao bookHistoryDao;
    private final TransactionTemplate txTemplate;

    public BookService(@Nonnull BookDao bookDao,
                       @Nonnull BookHistoryDao bookHistoryDao,
                       @Nonnull TransactionTemplate txTemplate) {
        this.bookDao = Objects.requireNonNull(bookDao);
        this.bookHistoryDao = Objects.requireNonNull(bookHistoryDao);
        this.txTemplate = Objects.requireNonNull(txTemplate);
    }

    public int addBook(@Nonnull BookEntity book, @Nonnull List<BookReadActivityEntity> readActivityList) {
        return txTemplate.execute(status -> {
            int bookId = bookDao.store(book);
            bookHistoryDao.store(readActivityList.stream()
                    .map(readActivity -> BookReadActivityEntity.builder(readActivity).withBookId(bookId).build())
                    .collect(Collectors.toList()));
            return bookId;
        });
    }
}
