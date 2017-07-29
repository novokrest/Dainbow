package com.oneuse.dainbow.books.extensions;

import com.oneuse.dainbow.books.domain.PageRange;

public class PageRangeEx {
    public static int pagesCount(PageRange pageRange) {
        return pageRange.getEnd() - pageRange.getBegin();
    }
}
