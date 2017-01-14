package com.oneuse.dainbow;

public interface BookPages extends Iterable<PageRange> {
    Iterable<Integer> getPages();

    void addPage(int pageNumber);
    void addPages(PageRange pageRange);
}
