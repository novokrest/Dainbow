package com.oneuse.core;

public class CharIterator {
    private final String str;
    private int current;

    public CharIterator(String str) {
        this.str = str;
    }

    public void moveNext() {
        ++current;
    }

    public char current() {
        return str.charAt(current);
    }

    public boolean isEnd() {
        return current > str.length() - 1;
    }
}
