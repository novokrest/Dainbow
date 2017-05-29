package com.oneuse.dainbow.books.storage;

import com.oneuse.core.Verifiers;
import com.oneuse.dainbow.books.domain.Book;
import com.oneuse.dainbow.books.utils.BookUtils;
import com.oneuse.dainbow.books.domain.PageRange;
import com.oneuse.dainbow.books.builders.BookBuilder;
import com.oneuse.dainbow.books.config.PersistenceConfig;
import com.oneuse.dainbow.books.config.RootConfig;
import com.oneuse.dainbow.books.config.WebConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, PersistenceConfig.class, WebConfig.class})
@WebAppConfiguration
@ActiveProfiles({"test"})
public class JdbcBookRepositoryTest {
    @Autowired
    private JdbcBookRepository repository;

    @Test
    public void testConnection() {
        Book book = repository.findOne(1);
        Assert.assertNotNull(book);
    }

    @Test
    public void test_AddBookWithAllInfo_Expect_BookAddedSuccessfully() {
        Book book = Book.createNewBook("Test Title", "Test Author", 345, BookUtils.defaultCoverImage());
        Book addedBook = repository.addBook(book);
        repository.clearCache();
        Book foundBook = repository.findOne(addedBook.getId());
        Verifiers.verify(foundBook.equals(addedBook));
    }

    @Test
    public void test_AddBookWithoutCover_Expect_BookAddedSuccessfully() {
        Book book = new BookBuilder()
                .setTitle("title1")
                .setAuthor("author1")
                .setTotalPagesCount(100)
                .setCoverImage(null)
                .build();

        Book addedBook = repository.addBook(book);
        repository.clearCache();
        Book foundBook = repository.findOne(addedBook.getId());

        Assert.assertEquals(addedBook, foundBook);
    }

    @Test
    public void testAddingBookWithReadPages() {
        Book book = BookUtils.createBook(0);
        book.addReadPages(new PageRange(1, 10));
        book.addReadPages(new PageRange(15, 50));
        book.addReadPages(new PageRange(78, 90));

        Book addedBook = repository.addBook(book);
        repository.clearCache();
        Book foundBook = repository.findOne(addedBook.getId());

        Assert.assertEquals(addedBook, foundBook);
    }

    @Test
    public void test_FindBookWithoutCover_Expect_BookWithCoverEqualsToNull() {
        Book book = new BookBuilder()
                .setTitle("book1")
                .setAuthor("author1")
                .setTotalPagesCount(100)
                .setCoverImage(null)
                .build();

        Book addedBook = repository.addBook(book);
        repository.clearCache();
        Book foundBook = repository.findOne(addedBook.getId());

        Assert.assertNull(foundBook.getCoverImage());
    }

    @Test
    public void testFindAll() {
        List<Book> books = repository.findAll();
        Assert.assertNotNull(books);
    }
}
