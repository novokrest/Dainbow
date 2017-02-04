package com.oneuse.dainbow.builders;

import com.oneuse.dainbow.Book;
import com.oneuse.dainbow.image.Image;
import com.oneuse.dainbow.image.ImageType;

public class BookBuilder {
    private String title;
    private String author;
    private int totalPagesCount;
    private Image image;

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

    public BookBuilder setEmptyImage() {
        this.image = new Image(ImageType.JPEG, new byte[0]);
        return this;
    }

    public Book build() {
        return Book.createNewBook(title, author, totalPagesCount, image);
    }
}
