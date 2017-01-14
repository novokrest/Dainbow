package com.oneuse.dainbow.storage;

import com.oneuse.core.Verifiers;
import com.oneuse.dainbow.Book;
import com.oneuse.dainbow.BookUtils;
import com.oneuse.dainbow.PageRange;
import com.oneuse.dainbow.config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
public class JdbcBookRepositoryTest {
    @Autowired
    private JdbcBookRepository repository;

    @Test
    public void testConnection() {
        Book book = repository.findOne(1);
        Assert.notNull(book);
    }

    @Test
    public void testAddingBook() {
        Book book = Book.createNewBook("Test Title", "Test Author", 345, BookUtils.defaultCoverImage());
        Book addedBook = repository.addBook(book);

        Book foundBook = repository.findOne(addedBook.getId());
        Verifiers.verify(foundBook.equals(addedBook));
    }

    @Test
    public void testAddingBookWithReadPages() {
        Book book = BookUtils.createBook(0);
        book.addReadPages(new PageRange(1, 10));
        book.addReadPages(new PageRange(15, 50));
        book.addReadPages(new PageRange(78, 90));

        Book addedBook = repository.addBook(book);
        Book foundBook = repository.findOne(addedBook.getId());

        Verifiers.verify(addedBook.equals(foundBook));
    }

    @Test
    public void testFindAll() {
        List<Book> books = repository.findAll();
        Assert.notEmpty(books);
    }
}
