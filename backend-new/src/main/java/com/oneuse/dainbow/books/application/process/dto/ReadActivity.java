package com.oneuse.dainbow.books.application.process.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.annotation.Nonnull;

/**
 * Read Activity data
 *
 * @author Konstantin Novokreshchenov (novokrest013@gmail.com)
 * @since 24.12.2017
 */
@ApiModel("<h5>Read Activity</h5>")
public class ReadActivity {

    @ApiModelProperty(
            value = "<h6>Begin Page</h6>",
            required = true,
            example = "10"
    )
    private final Integer beginPage;

    @ApiModelProperty(
            value = "<h6>End Page</h6>",
            required = true,
            example = "15"
    )
    private final Integer endPage;

    @JsonCreator
    public ReadActivity(@JsonProperty("beginPage") @Nonnull Integer beginPage,
                        @JsonProperty("endPage") @Nonnull Integer endPage) {
        this.beginPage = beginPage;
        this.endPage = endPage;
    }

    @Nonnull
    @JsonProperty("beginPage")
    public Integer getBeginPage() {
        return beginPage;
    }

    @Nonnull
    @JsonProperty("endPage")
    public Integer getEndPage() {
        return endPage;
    }

    @Override
    public String toString() {
        return "ReadActivity" +
                "beginPage=" + beginPage +
                ", endPage=" + endPage +
                '}';
    }

    /**
     * Создает билдер для построения объекта {@link ReadActivity}
     *
     * @return билдер для построения объекта {@link ReadActivity}
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
    public static Builder builder(ReadActivity copy) {
        Builder builder = new Builder();
        builder.beginPage = copy.beginPage;
        builder.endPage = copy.endPage;
        return builder;
    }

    /**
     * Вспомогательный класс для построения объекта типа {@link ReadActivity}
     */
    public static class Builder {

        private Integer beginPage;
        private Integer endPage;

        private Builder() {
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

        /**
         * Создает новый объект типа {@link ReadActivity}
         *
         * @return новый объект типа {@link ReadActivity}
         */
        @Nonnull
        public ReadActivity build() {
            return new ReadActivity(
                    beginPage,
                    endPage
            );
        }
    }

}