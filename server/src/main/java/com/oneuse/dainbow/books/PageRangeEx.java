package com.oneuse.dainbow.books;

public class PageRangeEx {
    public static int pagesCount(PageRange pageRange) {
        return pageRange.getEnd() - pageRange.getBegin();
    }
}
