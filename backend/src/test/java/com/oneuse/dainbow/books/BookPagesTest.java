package com.oneuse.dainbow.books;

import com.oneuse.core.CollectionEx;
import com.oneuse.dainbow.books.domain.BookPagesImpl;
import com.oneuse.dainbow.books.domain.PageRange;
import com.oneuse.dainbow.books.extensions.BookPagesEx;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookPagesTest {

    @Test
    public void testEmptyBookPages() {
        testBookPages(new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void testAddingOnePage() {
        List<PageRange> pageRanges = new ArrayList<PageRange>() {{ add(new PageRange(55)); }};
        testBookPages(pageRanges, pageRanges);
    }

    @Test
    public void testAddingOnePageRange() {
        List<PageRange> pageRanges = new ArrayList<PageRange>() {{ add(new PageRange(1, 10)); }};
        testBookPages(pageRanges, pageRanges);
    }

    @Test
    public void testAddingNonOverlappedPageRanges() {
        List<PageRange> pageRanges = new ArrayList<PageRange>() {{
                add(new PageRange(1, 10));
                add(new PageRange(11, 20));
                add(new PageRange(21, 30));
                add(new PageRange(41, 50));
        }};

        List<PageRange> expectedPageRanges = new ArrayList<PageRange>() {{
            add(new PageRange(1, 30));
            add(new PageRange(41, 50));
        }};

        testBookPages(pageRanges, expectedPageRanges);
    }

    @Test
    public void testAddingOverlappedPageRanges() {
        List<PageRange> addedPageRange = new ArrayList<PageRange>(){{
            add(new PageRange(1, 20));
            add(new PageRange(15, 50));
            add(new PageRange(60, 70));
            add(new PageRange(65, 67));
            add(new PageRange(100, 150));
            add(new PageRange(110, 110));
            add(new PageRange(150, 200));
            add(new PageRange(200, 250));
            add(new PageRange(251, 251));
        }};
        List<PageRange> expectedPageRange = new ArrayList<PageRange>() {{
            add(new PageRange(1, 50));
            add(new PageRange(60, 70));
            add(new PageRange(100, 251));
        }};

        testBookPages(addedPageRange, expectedPageRange);
    }

    private void testBookPages(Collection<PageRange> addedPageRanges, Collection<PageRange> expectedPageRanges) {
        BookPagesImpl bookPages = createBookPages(addedPageRanges);
        Collection<PageRange> actualPageRanges = CollectionEx.fromIterable(bookPages);
        Assert.assertEquals(expectedPageRanges, actualPageRanges);
    }

    private static BookPagesImpl createBookPages(Collection<PageRange> pageRanges) {
        BookPagesImpl bookPages = new BookPagesImpl();
        BookPagesEx.addPageRanges(bookPages, pageRanges);
        return bookPages;
    }
}
