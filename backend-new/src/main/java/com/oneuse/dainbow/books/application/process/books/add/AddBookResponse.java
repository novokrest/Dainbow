package com.oneuse.dainbow.books.application.process.books.add;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.annotation.Nonnull;


/**
 * Response data for adding book command
 *
 * @author Konstantin Novokreshchenov (knovokresch@yamoney.ru)
 * @since 24.12.2017
 */
@ApiModel("<h5>Response data for adding book command</h5>")
public class AddBookResponse {

    @ApiModelProperty(
            value = "<h6>Book ID</h6>",
            required = true,
            example = "10"
    )
    private final Integer bookId;

    @JsonCreator
    public AddBookResponse(@JsonProperty("bookId") @Nonnull Integer bookId) {
        this.bookId = bookId;
    }

    @Nonnull
    @JsonProperty("bookId")
    public Integer getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return "AddBookResponse" +
                "bookId=" + bookId +
                '}';
    }

    /**
     * Создает билдер для построения объекта {@link AddBookResponse}
     *
     * @return билдер для построения объекта {@link AddBookResponse}
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
    public static Builder builder(AddBookResponse copy) {
        Builder builder = new Builder();
        builder.bookId = copy.bookId;
        return builder;
    }

    /**
     * Вспомогательный класс для построения объекта типа {@link AddBookResponse}
     */
    public static class Builder {

        private Integer bookId;

        private Builder() {
        }

        @Nonnull
        public Builder withBookId(@Nonnull Integer bookId) {
            this.bookId = bookId;
            return this;
        }

        /**
         * Создает новый объект типа {@link AddBookResponse}
         *
         * @return новый объект типа {@link AddBookResponse}
         */
        @Nonnull
        public AddBookResponse build() {
            return new AddBookResponse(
                    bookId
            );
        }
    }
}