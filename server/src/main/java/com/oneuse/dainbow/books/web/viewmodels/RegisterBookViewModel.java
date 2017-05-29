package com.oneuse.dainbow.books.web.viewmodels;

import com.oneuse.dainbow.books.dto.BookDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class RegisterBookViewModel implements BookDTO {
    @Size(min=1, message = "{title.size}")
    private String title;

    @Size(min=1, max=100, message = "{author.size}")
    private String author;

    @Min(value = 1, message = "{totalPagesCount.min}")
    private int totalPagesCount;

    private String coverBase64;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setTotalPagesCount(int totalPagesCount) {
        this.totalPagesCount = totalPagesCount;
    }

    public int getTotalPagesCount() {
        return totalPagesCount;
    }

    public String getCoverBase64() {
        return coverBase64;
    }

    public void setCoverBase64(String coverBase64) {
        this.coverBase64 = coverBase64;
    }
}
