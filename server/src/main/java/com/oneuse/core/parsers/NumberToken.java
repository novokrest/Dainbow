package com.oneuse.core.parsers;

public class NumberToken extends Token {
    private final int number;

    public NumberToken(int number) {
        super(Integer.toString(number));
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
