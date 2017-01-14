package com.oneuse.core.parsers;

import com.oneuse.core.CharIterator;
import com.oneuse.core.Verifiers;

public class NumberParser {
    public int parse(CharIterator chars) {
        Verifiers.verify(Character.isDigit(chars.current()),
                                           "Incorrect character, expect digit, received %c", chars.current());
        int number = 0;
        do {
            number *= 10;
            number += chars.current() - '0';
            chars.moveNext();
        } while (!chars.isEnd() && Character.isDigit(chars.current()));

        return number;
    }
}
