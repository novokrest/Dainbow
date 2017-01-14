package com.oneuse.dainbow.web.viewmodels;

public class PageViewModel {
    private int number;
    private boolean read;

    public PageViewModel(int number, boolean read) {
        this.number = number;
        this.read = read;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
