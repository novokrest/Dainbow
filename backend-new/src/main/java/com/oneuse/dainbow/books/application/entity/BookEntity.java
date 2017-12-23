package com.oneuse.dainbow.books.application.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.annotation.Nonnull;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

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

    private BookEntity() {

    }

    private BookEntity(Integer id,
                       String title,
                       String author,
                       Integer totalPagesCount,
                       String coverImage) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.totalPagesCount = totalPagesCount;
        this.coverImage = coverImage;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    /**
     * Создает билдер для построения объекта {@link BookEntity}
     *
     * @return билдер для построения объекта {@link BookEntity}
     */
    public static Builder builder() {
        return new Builder();
    }


    /**
     * Создает билдер с данными из переданного объекта
     *
     * @param copy объект, данные которого копируются в билдер
     * @return билдер с данными из переданного объекта
     */
    public static Builder builder(BookEntity copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.title = copy.title;
        builder.author = copy.author;
        builder.totalPagesCount = copy.totalPagesCount;
        builder.coverImage = copy.coverImage;
        return builder;
    }

    /**
     * Вспомогательный класс для построения объекта типа {@link BookEntity}
     */
    public static class Builder {

        private Integer id;
        private String title;
        private String author;
        private Integer totalPagesCount;
        private String coverImage;

        private Builder() {
        }

        @Nonnull
        public Builder withId(@Nonnull Integer id) {
            this.id = id;
            return this;
        }

        @Nonnull
        public Builder withTitle(@Nonnull String title) {
            this.title = title;
            return this;
        }

        @Nonnull
        public Builder withAuthor(@Nonnull String author) {
            this.author = author;
            return this;
        }

        @Nonnull
        public Builder withTotalPagesCount(@Nonnull Integer totalPagesCount) {
            this.totalPagesCount = totalPagesCount;
            return this;
        }

        @Nonnull
        public Builder withCoverImage(@Nonnull String coverImage) {
            this.coverImage = coverImage;
            return this;
        }

        /**
         * Создает новый объект типа {@link BookEntity}
         *
         * @return новый объект типа {@link BookEntity}
         */
        @Nonnull
        public BookEntity build() {
            return new BookEntity(
                    id,
                    title,
                    author,
                    totalPagesCount,
                    coverImage
            );
        }
    }

}

