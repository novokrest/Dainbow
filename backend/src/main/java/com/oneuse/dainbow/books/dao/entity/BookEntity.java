package com.oneuse.dainbow.books.dao.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "pages_count")
    private int totalPagesCount;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "cover_img")
    private String coverImage;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "book", cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE})
    private BookReadProgressEntity readProgress;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE})
    private List<BookReadActivityEntity> readHistories = new ArrayList<>();

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

    public int getTotalPagesCount() {
        return totalPagesCount;
    }

    public void setTotalPagesCount(int totalPagesCount) {
        this.totalPagesCount = totalPagesCount;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public BookReadProgressEntity getReadProgress() {
        return readProgress;
    }

    public void setReadProgress(BookReadProgressEntity readProgress) {
        this.readProgress = readProgress;
    }

    public List<BookReadActivityEntity> getReadHistories() {
        return readHistories;
    }

    public void setReadHistories(List<BookReadActivityEntity> readHistories) {
        this.readHistories = readHistories;
    }
}
