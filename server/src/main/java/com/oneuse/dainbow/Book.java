package com.oneuse.dainbow;


import com.oneuse.dainbow.image.Image;

public class Book extends DomainObject {
    private final String title;
    private final String author;
    private final int totalPagesCount;
    private final BookPages readPages;
    private final Image coverImage;

    public static Book createNewBook(String title, String author, int totalPagesCount, Image coverImage) {
        return new Book(0, title, author, totalPagesCount, new BookPagesImpl(), coverImage);
    }

    public Book(long id, String title, String author, int totalPagesCount, BookPages readPages, Image coverImage) {
        super(id);

        this.title = title;
        this.author = author;
        this.totalPagesCount = totalPagesCount;
        this.readPages = readPages;
        this.coverImage = coverImage;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalPagesCount() {
        return totalPagesCount;
    }

    public BookPages getReadPages() {
        return readPages;
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public void addReadPages(PageRange pageRange) {
        readPages.addPages(pageRange);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Book)) return false;

        Book other = (Book) obj;
        return title.equals(other.title) &&
               author.equals(other.author) &&
               totalPagesCount == other.totalPagesCount &&
               readPages.equals(other.readPages) &&
               coverImage.equals(other.coverImage);
    }
}
