package com.oneuse.dainbow.books.domain;

public interface BookPages extends Iterable<PageRange> {
    Iterable<Integer> getPages();
    int getPagesCount();

    void addPage(int pageNumber);
    void addPages(PageRange pageRange);
}
