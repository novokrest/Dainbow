package com.oneuse.dainbow.books;

import com.oneuse.core.CharIterator;
import com.oneuse.core.NumberRange;
import com.oneuse.core.parsers.NumberRangesParser;

import java.util.List;


public class BookPagesParser {
    private final NumberRangesParser numberRangesParser = new NumberRangesParser();

    public static BookPagesImpl parse(String text) {
        CharIterator chars = new CharIterator(text);
        return new BookPagesParser().parse(chars);
    }

    public BookPagesImpl parse(CharIterator chars) {
        List<NumberRange> numberRanges = numberRangesParser.parse(chars);

        BookPagesImpl bookPages = new BookPagesImpl();
        for (NumberRange numberRange: numberRanges) {
            bookPages.addPages(new PageRange(numberRange.getBegin(), numberRange.getEnd()));
        }
        return bookPages;
    }
}
