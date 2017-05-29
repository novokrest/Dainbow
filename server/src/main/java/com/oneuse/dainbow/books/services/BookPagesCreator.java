package com.oneuse.dainbow.books.services;

import com.oneuse.dainbow.books.domain.PageRange;
import com.oneuse.dainbow.books.domain.BookPages;
import com.oneuse.dainbow.books.domain.BookPagesImpl;

public class BookPagesCreator {
    public static BookPages create(int beginPage, int endPage) {
        BookPages bookPages = new BookPagesImpl();
        bookPages.addPages(new PageRange(beginPage, endPage));
        return bookPages;
    }
}
