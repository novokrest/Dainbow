package com.oneuse.dainbow.books.storage;

import com.oneuse.core.Verifiers;
import com.oneuse.dainbow.books.Book;
import com.oneuse.dainbow.books.BookPages;
import com.oneuse.dainbow.books.BookUtils;
import com.oneuse.dainbow.books.storage.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class FakeBookRepository implements BookRepository {
    private static final int BOOKS_COUNT = 500;
    private final List<Book> books;

    public FakeBookRepository() {
        books = BookUtils.createBooks(BOOKS_COUNT);
    }

    public List<Book> find(int count) {
        Verifiers.verify(count <= books.size(),
                         "There are ot enough books in repository: stored = %d, requested = %d", books.size(), count);
        return new ArrayList<>(books.subList(0, count));
    }

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    public Book findOne(long bookId) {
        final long finalBookId = bookId;
        return books.stream()
                    .filter(book -> book.getId() == finalBookId)
                    .findFirst()
                    .get();
    }

    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    @Override
    public void addBookPages(long bookId, BookPages bookPages) {

    }
}
