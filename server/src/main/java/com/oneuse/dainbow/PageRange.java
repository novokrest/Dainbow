package com.oneuse.dainbow;

import com.oneuse.core.Range;
import com.oneuse.core.Verifiers;

public class PageRange extends Range<Integer> {
    public PageRange(int page) {
        this(page, page);
    }

    public PageRange(int beginPage, int endPage) {
        super(beginPage, endPage);
        Verifiers.verify(beginPage > 0 && endPage > 0, "Pages must be positive");
    }
}
