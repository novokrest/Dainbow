package com.oneuse.dainbow.books.repositories.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity(name = "book_read_progress")
public class BookReadProgressEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "read_pages_count")
    private int readPagesCount;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private BookEntity book;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReadPagesCount() {
        return readPagesCount;
    }

    public void setReadPagesCount(int readPagesCount) {
        this.readPagesCount = readPagesCount;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public Long getBookId() {
        return book.getId();
    }
}
