package com.oneuse.dainbow.books.application.entity;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.awt.print.Book;
import java.sql.Timestamp;

/**
 * Information about one-time read activity
 *
 * @author Konstantin Novokreshchenov (knovokresch@yamoney.ru)
 * @since 17.06.2017
 */
@Entity(name = "book_read_history")
public class BookReadActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "begin_page")
    private Integer beginPage;

    @Column(name = "end_page")
    private Integer endPage;

    @Column(name = "begin_time")
    private Timestamp beginTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    private BookReadActivityEntity() {

    }

    private BookReadActivityEntity(Long id,
                                   Long bookId,
                                   Integer beginPage,
                                   Integer endPage,
                                   Timestamp beginTime,
                                   Timestamp endTime) {
        this.id = id;
        this.bookId = bookId;
        this.beginPage = beginPage;
        this.endPage = endPage;
        this.beginTime = beginTime;
        this.endTime = endTime;
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

    public Integer getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(Integer beginPage) {
        this.beginPage = beginPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    /**
     * Создает билдер для построения объекта {@link BookReadActivityEntity}
     *
     * @return билдер для построения объекта {@link BookReadActivityEntity}
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
    public static Builder builder(BookReadActivityEntity copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.bookId = copy.bookId;
        builder.beginPage = copy.beginPage;
        builder.endPage = copy.endPage;
        builder.beginTime = copy.beginTime;
        builder.endTime = copy.endTime;
        return builder;
    }

    /**
     * Вспомогательный класс для построения объекта типа {@link BookReadActivityEntity}
     */
    public static class Builder {

        private Long id;
        private Long bookId;
        private Integer beginPage;
        private Integer endPage;
        private Timestamp beginTime;
        private Timestamp endTime;

        private Builder() {
        }

        @Nonnull
        public Builder withId(@Nonnull Long id) {
            this.id = id;
            return this;
        }

        @Nonnull
        public Builder withBookId(@Nonnull Long bookId) {
            this.bookId = bookId;
            return this;
        }

        @Nonnull
        public Builder withBeginPage(@Nonnull Integer beginPage) {
            this.beginPage = beginPage;
            return this;
        }

        @Nonnull
        public Builder withEndPage(@Nonnull Integer endPage) {
            this.endPage = endPage;
            return this;
        }

        @Nonnull
        public Builder withBeginTime(@Nonnull Timestamp beginTime) {
            this.beginTime = beginTime;
            return this;
        }

        @Nonnull
        public Builder withEndTime(@Nonnull Timestamp endTime) {
            this.endTime = endTime;
            return this;
        }

        /**
         * Создает новый объект типа {@link BookReadActivityEntity}
         *
         * @return новый объект типа {@link BookReadActivityEntity}
         */
        @Nonnull
        public BookReadActivityEntity build() {
            return new BookReadActivityEntity(
                    id,
                    bookId,
                    beginPage,
                    endPage,
                    beginTime,
                    endTime
            );
        }
    }

}
