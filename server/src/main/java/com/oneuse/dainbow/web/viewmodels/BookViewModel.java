package com.oneuse.dainbow.web.viewmodels;

import com.oneuse.dainbow.Book;
import com.oneuse.dainbow.BookPages;
import com.oneuse.dainbow.BookPagesImpl;
import com.oneuse.dainbow.PageRangeEx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.StreamSupport;

public class BookViewModel {
    private long id;
    private String title;
    private String author;
    private int totalPagesCount;
    private int readPagesCount;
    private int totalReadTime;
    private List<PageViewModel> pages;

    public static BookViewModel createFrom(Book book) {
        BookViewModel bookViewModel = new BookViewModel();

        bookViewModel.setId(book.getId());
        bookViewModel.setTitle(book.getTitle());
        bookViewModel.setAuthor(book.getAuthor());
        bookViewModel.setTotalPagesCount(book.getTotalPagesCount());

        int readPagesCount = StreamSupport.stream(book.getReadPages().spliterator(), false)
                                          .mapToInt(PageRangeEx::pagesCount)
                                          .sum();
        bookViewModel.setReadPagesCount(readPagesCount);

        int totalReadTime = 0;
        bookViewModel.setTotalReadTime(totalReadTime);

        bookViewModel.setPages(createPageViewModels(book));

        return bookViewModel;
    }

    private static List<PageViewModel> createPageViewModels(Book book) {
        List<PageViewModel> result = new ArrayList<>(Collections.nCopies(book.getTotalPagesCount(), null));

        BookPages readPages = book.getReadPages();
        for (Integer pageNumber: readPages.getPages()) {
            result.set(pageNumber - 1, new PageViewModel(pageNumber, true));
        }

        for (int pageNumber = 1, count = result.size(); pageNumber <= count; ++pageNumber) {
            if (result.get(pageNumber - 1) == null) {
                result.set(pageNumber - 1, new PageViewModel(pageNumber, false));
            }
        }

        return result;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalPagesCount() {
        return totalPagesCount;
    }

    public void setTotalPagesCount(int totalPagesCount) {
        this.totalPagesCount = totalPagesCount;
    }

    public int getReadPagesCount() {
        return readPagesCount;
    }

    public void setReadPagesCount(int readPagesCount) {
        this.readPagesCount = readPagesCount;
    }

    public int getTotalReadTime() {
        return totalReadTime;
    }

    public void setTotalReadTime(int totalReadTime) {
        this.totalReadTime = totalReadTime;
    }

    public List<PageViewModel> getPages() {
        return pages;
    }

    public void setPages(List<PageViewModel> pages) {
        this.pages = pages;
    }
}
