package com.oneuse.dainbow;

public interface BookPages extends Iterable<PageRange> {
    Iterable<Integer> getPages();
    int getPagesCount();

    void addPage(int pageNumber);
    void addPages(PageRange pageRange);
}
