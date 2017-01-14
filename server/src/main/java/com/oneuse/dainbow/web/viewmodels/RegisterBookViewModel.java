package com.oneuse.dainbow.web.viewmodels;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterBookViewModel {
    @NotNull
    @Size(min=1, message = "{title.size}")
    private String title;

    @NotNull
    @Size(min=1, max=100, message = "{author.size}")
    private String author;

    @Min(value = 1, message = "{totalPagesCount.min}")
    private int totalPagesCount;

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
}
