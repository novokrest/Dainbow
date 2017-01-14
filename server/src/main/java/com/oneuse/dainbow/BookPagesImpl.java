package com.oneuse.dainbow;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class BookPagesImpl implements BookPages {
    private final Set<Integer> pages = new TreeSet<>();

    public Iterable<Integer> getPages() {
        return pages;
    }

    @Override
    public int getPagesCount() {
        return pages.size();
    }

    public void addPages(PageRange pageRange) {
        addPageRange(pageRange);
    }

    public void addPage(int pageNumber) {
        addPageRange(new PageRange(pageNumber));
    }

    private void addPageRange(PageRange pageRange) {
        for (int i = pageRange.getBegin(); i <= pageRange.getEnd(); ++i) {
            pages.add(i);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof BookPagesImpl)) return false;

        BookPagesImpl other = (BookPagesImpl) obj;
        return pages.equals(other.pages);
    }

    @Override
    public Iterator<PageRange> iterator() {
        return new BookPageIterator();
    }

    private class BookPageIterator implements Iterator<PageRange> {
        private final Iterator<Integer> sortedPageIterator = pages.iterator();
        private boolean lastHasNext = sortedPageIterator.hasNext();
        private int lastNextPageNumber = sortedPageIterator.hasNext() ? sortedPageIterator.next() : 0;

        @Override
        public boolean hasNext() {
            return lastHasNext;
        }

        @Override
        public PageRange next() {
            int beginPage = lastNextPageNumber;
            int endPage = beginPage;
            while (lastHasNext = sortedPageIterator.hasNext()) {
                int nextPage = sortedPageIterator.next();
                if (nextPage == endPage + 1) {
                    endPage = nextPage;
                }
                else {
                    lastNextPageNumber = nextPage;
                    break;
                }
            }
            return new PageRange(beginPage, endPage);
        }
    }
}

