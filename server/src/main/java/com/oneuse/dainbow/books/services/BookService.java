package com.oneuse.dainbow.books.services;

import com.oneuse.core.Converter;
import com.oneuse.core.DataUrlUtils;
import com.oneuse.core.time.DayPeriod;
import com.oneuse.dainbow.books.dao.BookDao;
import com.oneuse.dainbow.books.domain.Book;
import com.oneuse.dainbow.books.domain.ReadActivity;
import com.oneuse.dainbow.books.domain.ReadHistory;
import com.oneuse.dainbow.books.dto.BookDTO;
import com.oneuse.dainbow.books.dto.LogReadActivityDTO;
import com.oneuse.dainbow.books.image.Image;
import com.oneuse.dainbow.books.storage.BookRepository;
import com.oneuse.dainbow.books.storage.ReadHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class BookService {
    @Deprecated
    private final BookRepository bookRepository;
    private final BookDao bookDao;
    private final ReadHistoryRepository readHistoryRepository;

    @Autowired
    public BookService(@Deprecated BookRepository bookRepository,
                       BookDao bookDao,
                       ReadHistoryRepository readHistoryRepository) {
        this.bookRepository = bookRepository;
        this.bookDao = bookDao;
        this.readHistoryRepository = readHistoryRepository;
    }

    public List<Book> findAllBooks() {
        return StreamSupport.stream(bookDao.findAll().spliterator(), false)
                .map(e -> Book.of(e.getId(), e.getTitle(), e.getAuthor(), e.getTotalPagesCount()))
                .collect(Collectors.toList());
    }

    public Book findBook(long bookId) {
        return bookRepository.findOne(bookId);
    }

    public Book addBook(BookDTO book) {
        Image cover = DataUrlUtils.decode(book.getCoverBase64());
        Book newBook = Book.of(book.getTitle(), book.getAuthor(), book.getTotalPagesCount(), cover);
        return bookRepository.addBook(newBook);
    }

    public void logReadActivity(long bookId, LogReadActivityDTO logReadActivity) {
        Converter<LogReadActivityDTO, ReadActivity> converter = value -> {
            DayPeriod period = new DayPeriod(value.getDay(), value.getBeginTime(), value.getEndTime());
            return new ReadActivity(value.getPages(), period);
        };

        bookRepository.addBookPages(bookId, logReadActivity.getPages());
        readHistoryRepository.logReadActivity(bookId, converter.convert(logReadActivity));
    }

    public ReadHistory findBookReadHistory(long bookId) {
        return readHistoryRepository.findReadHistory(bookId);
    }

    public Image findBookCover(long bookId) {
        return bookRepository.findOne(bookId).getCoverImage();
    }
}
