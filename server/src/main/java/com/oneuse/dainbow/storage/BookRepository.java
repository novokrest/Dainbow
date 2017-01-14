package com.oneuse.dainbow.storage;

import com.oneuse.dainbow.Book;
import com.oneuse.dainbow.BookPages;

import java.util.List;


public interface BookRepository {
    List<Book> find(int count);
    List<Book> findAll();

    Book findOne(long bookId);
    Book addBook(Book book);

    void addBookPages(long bookId, BookPages bookPages);
}
