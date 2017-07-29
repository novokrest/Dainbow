package com.oneuse.core;

public class CharIteratorEx {
    public static void skipSpaces(CharIterator chars) {
        while (!chars.isEnd() && Character.isWhitespace(chars.current())) {
            chars.moveNext();
        }
    }
}
