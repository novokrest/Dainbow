package com.oneuse.dainbow.books.builders;

import com.oneuse.dainbow.books.domain.Book;
import com.oneuse.dainbow.books.image.Image;

public class BookBuilder {
    private long id;
    private String title;
    private String author;
    private int totalPagesCount;
    private Image image;

    public BookBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder setTotalPagesCount(int pagesCount) {
        this.totalPagesCount = pagesCount;
        return this;
    }

    public BookBuilder setCoverImage(Image cover) {
        this.image = cover;
        return this;
    }

    public Book build() {
        return Book.of(id, title, author, totalPagesCount, image);
    }
}
