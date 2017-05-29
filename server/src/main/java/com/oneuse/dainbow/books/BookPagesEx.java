package com.oneuse.dainbow.books;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class BookPagesEx {
    public static void addPageRanges(BookPages bookPages, PageRange[] pageRange) {
        addPageRanges(bookPages, Arrays.stream(pageRange));
    }

    public static void addPageRanges(BookPages bookPages, Collection<PageRange> pageRange) {
        addPageRanges(bookPages, pageRange.stream());
    }

    public static void addPageRanges(BookPages bookPages, Stream<PageRange> pageRanges) {
        pageRanges.forEach(pageRange -> bookPages.addPages(pageRange));
    }

    public static String toString(BookPages bookPages) {
        Function<PageRange, String> pageRangePrinter = pageRange -> pageRange.getBegin() == pageRange.getEnd()
                                                                    ? Integer.toString(pageRange.getBegin())
                                                                    : String.format("%d - %d", pageRange.getBegin(), pageRange.getEnd());

        List<String> pageRangeStrings = StreamSupport.stream(bookPages.spliterator(), false)
                                                      .map(pageRangePrinter)
                                                      .collect(Collectors.toList());

        return String.join(", ", pageRangeStrings);
    }

    public static BookPages add(BookPages result, BookPages added) {
        for (PageRange pageRange : added) {
            result.addPages(pageRange);
        }
        return result;
    }
}
