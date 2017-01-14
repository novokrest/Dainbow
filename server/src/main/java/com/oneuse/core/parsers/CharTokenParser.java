package com.oneuse.core.parsers;

import com.oneuse.core.CharIterator;

public class CharTokenParser {
    public static final CharTokenParser DASH_PARSER = new CharTokenParser('-');
    public static final CharTokenParser COMMA_PARSER = new CharTokenParser(',');

    private final char tokenChar;
    private final String tokenString;

    public CharTokenParser(char tokenValue) {
        this.tokenChar = tokenValue;
        this.tokenString = new String(new char[] { tokenValue });
    }

    public boolean parse(CharIterator iterator) {
        if (iterator.current() == tokenChar) {
            iterator.moveNext();
            return true;
        }

        return false;
    }

    public Token getToken() {
        return new Token(tokenString);
    }
}
