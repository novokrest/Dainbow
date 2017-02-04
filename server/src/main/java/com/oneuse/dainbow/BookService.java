package com.oneuse.dainbow;

import com.oneuse.core.Converter;
import com.oneuse.core.time.DayPeriod;
import com.oneuse.dainbow.book.ReadHistory;
import com.oneuse.dainbow.image.Image;
import com.oneuse.dainbow.storage.BookRepository;
import com.oneuse.dainbow.storage.ReadHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookService {
    private final BookRepository bookRepository;
    private final BookCoverProvider bookCoverProvider;
    private final ReadHistoryRepository readHistoryRepository;

    @Autowired
    public BookService(BookRepository bookRepository,
                       BookCoverProvider bookCoverProvider,
                       ReadHistoryRepository readHistoryRepository) {
        this.bookRepository = bookRepository;
        this.bookCoverProvider = bookCoverProvider;
        this.readHistoryRepository = readHistoryRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBook(long bookId) {
        return bookRepository.findOne(bookId);
    }

    public Image findBookCover(long bookId) {
        return bookCoverProvider.findCover(bookId);
    }

    public Book addBook(BookDTO book, Image cover) {
        Book newBook = Book.createNewBook(book.getTitle(), book.getAuthor(), book.getTotalPagesCount(), cover);
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
}
