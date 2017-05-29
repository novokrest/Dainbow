package com.oneuse.dainbow.books;

public class BookPagesCreator {
    public static BookPages create(int beginPage, int endPage) {
        BookPages bookPages = new BookPagesImpl();
        bookPages.addPages(new PageRange(beginPage, endPage));
        return bookPages;
    }
}
