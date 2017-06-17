package com.oneuse.dainbow.books.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Данные о книге
 *
 * @author Konstantin Novokreshchenov (knovokresch@yamoney.ru)
 * @since 17.06.2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    private final String title;
    private final String author;
    private final Integer totalPagesCount;
    private final String readPages;

    public Book(@JsonProperty("title") String title,
                @JsonProperty("author") String author,
                @JsonProperty("totalPagesCount") Integer totalPagesCount,
                @JsonProperty("readPages") String readPages) {
        this.title = title;
        this.author = author;
        this.totalPagesCount = totalPagesCount;
        this.readPages = readPages;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("totalPagesCount")
    public Integer getTotalPagesCount() {
        return totalPagesCount;
    }

    @JsonProperty("readPages")
    public String getReadPages() {
        return readPages;
    }

    @Override
    public String toString() {
        return "{" +
                "'title': '" + title + "'" +
                ", 'author': '" + author + "'" +
                ", 'totalPagesCount': '" + totalPagesCount + "'" +
                ", 'readPages': '" + readPages + "'" +
                "}";
    }
}
