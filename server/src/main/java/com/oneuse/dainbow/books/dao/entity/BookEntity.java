package com.oneuse.dainbow.books.dao.entity;

import javax.persistence.*;


@Entity(name = "book")
public class BookEntity {

    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "pages_count")
    private int pagesCount;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "cover_img")
    private String coverImage;

    private BookEntity() { }

    public BookEntity(String title, String author, int pagesCount) {
        this(title, author, pagesCount, null);
    }

    public BookEntity(String title, String author, int pagesCount, String coverImage) {
        this.title = title;
        this.author = author;
        this.pagesCount = pagesCount;
        this.coverImage = coverImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
