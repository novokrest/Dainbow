package com.oneuse.dainbow.books.application.config;

import com.oneuse.dainbow.books.application.dao.BookDao;
import com.oneuse.dainbow.books.application.dao.BookHistoryDao;
import com.oneuse.dainbow.books.application.process.books.BooksController;
import com.oneuse.dainbow.books.application.process.books.add.AddBookCommand;
import com.oneuse.dainbow.books.application.services.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class BooksConfiguration {

    @Bean
    BooksController booksController(AddBookCommand addBookCommand) {
        return new BooksController(addBookCommand);
    }

    @Bean
    AddBookCommand addBookCommand(BookService bookService) {
        return new AddBookCommand(bookService);
    }

    @Bean
    BookService bookService(BookDao bookDao, BookHistoryDao bookHistoryDao, TransactionTemplate txTemplate) {
        return new BookService(bookDao, bookHistoryDao, txTemplate);
    }
}
