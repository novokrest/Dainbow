package com.oneuse.dainbow.builders;

import com.oneuse.dainbow.Book;
import com.oneuse.dainbow.image.Image;
import com.oneuse.dainbow.image.ImageType;

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
        return Book.createNewBook(id, title, author, totalPagesCount, image);
    }
}
