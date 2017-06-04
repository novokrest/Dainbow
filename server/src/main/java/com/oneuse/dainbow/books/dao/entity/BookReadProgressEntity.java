package com.oneuse.dainbow.books.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "book_read_progress")
public class BookReadProgressEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "read_pages_count")
    private Long readPagesCount;

    private BookReadProgressEntity() { }

    public BookReadProgressEntity(Long bookId, Long readPagesCount) {
        this.bookId = bookId;
        this.readPagesCount = readPagesCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getReadPagesCount() {
        return readPagesCount;
    }

    public void setReadPagesCount(Long readPagesCount) {
        this.readPagesCount = readPagesCount;
    }
}
