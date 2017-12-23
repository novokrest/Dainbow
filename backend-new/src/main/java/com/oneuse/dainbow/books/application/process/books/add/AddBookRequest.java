package com.oneuse.dainbow.books.application.process.books.add;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oneuse.dainbow.books.application.process.dto.ReadActivity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

/**
 * Request data for adding book
 *
 * @author Konstantin Novokreshchenov (novokrest013@gmail.com)
 * @since 24.12.2017
 */
@ApiModel("<h5>Request data for adding book</h5>")
public class AddBookRequest {

    @ApiModelProperty(
            value = "<h6>Title</h6>",
            required = true,
            example = "Java Concurrency in Practice"
    )
    private final String title;

    @ApiModelProperty(
            value = "<h6>Author</h6>",
            required = true,
            example = "Brian Goetz"
    )
    private final String author;

    @ApiModelProperty(
            value = "<h6>Total pages count</h6>",
            required = true,
            example = "551"
    )
    private final Integer totalPagesCount;

    @ApiModelProperty(
            value = "<h6>Read pages count</h6>",
            required = true,
            example = "113"
    )
    private final Integer readPagesCount;

    @ApiModelProperty(
            value = "<h6>Read activities</h6>",
            required = true
    )
    private final List<ReadActivity> readActivities;

    @JsonCreator
    AddBookRequest(@JsonProperty("title") @Nonnull String title,
                   @JsonProperty("author") @Nonnull String author,
                   @JsonProperty("totalPagesCount") @Nonnull Integer totalPagesCount,
                   @JsonProperty("readPagesCount") @Nonnull Integer readPagesCount,
                   @JsonProperty("readActivities") @Nonnull List<ReadActivity> readActivities) {
        this.title = title;
        this.author = author;
        this.totalPagesCount = totalPagesCount;
        this.readPagesCount = readPagesCount;
        this.readActivities = readActivities;
    }

    @Nonnull
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @Nonnull
    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @Nonnull
    @JsonProperty("totalPagesCount")
    public Integer getTotalPagesCount() {
        return totalPagesCount;
    }

    @Nonnull
    @JsonProperty("readPagesCount")
    public Integer getReadPagesCount() {
        return readPagesCount;
    }

    @Nonnull
    @JsonProperty("readActivities")
    public List<ReadActivity> getReadActivities() {
        return readActivities;
    }

    @Override
    public String toString() {
        return "AddBookRequest" +
                "title=" + title +
                ", author=" + author +
                ", totalPagesCount=" + totalPagesCount +
                ", readPagesCount=" + readPagesCount +
                ", readActivities=" + readActivities +
                '}';
    }

    /**
     * Создает билдер для построения объекта {@link AddBookRequest}
     *
     * @return билдер для построения объекта {@link AddBookRequest}
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
    public static Builder builder(AddBookRequest copy) {
        Builder builder = new Builder();
        builder.title = copy.title;
        builder.author = copy.author;
        builder.totalPagesCount = copy.totalPagesCount;
        builder.readPagesCount = copy.readPagesCount;
        builder.readActivities = copy.readActivities;
        return builder;
    }

    /**
     * Вспомогательный класс для построения объекта типа {@link AddBookRequest}
     */
    public static class Builder {

        private String title;
        private String author;
        private Integer totalPagesCount;
        private Integer readPagesCount;
        private List<ReadActivity> readActivities;

        private Builder() {
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
        public Builder withReadPagesCount(@Nonnull Integer readPagesCount) {
            this.readPagesCount = readPagesCount;
            return this;
        }

        @Nonnull
        public Builder withReadActivities(@Nonnull List<ReadActivity> readActivities) {
            this.readActivities = readActivities;
            return this;
        }

        /**
         * Создает новый объект типа {@link AddBookRequest}
         *
         * @return новый объект типа {@link AddBookRequest}
         */
        @Nonnull
        public AddBookRequest build() {
            return new AddBookRequest(
                    title,
                    author,
                    totalPagesCount,
                    readPagesCount,
                    readActivities
            );
        }
    }

}