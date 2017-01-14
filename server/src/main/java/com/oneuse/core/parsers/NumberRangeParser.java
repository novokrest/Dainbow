package com.oneuse.core.parsers;

import com.oneuse.core.CharIterator;
import com.oneuse.core.CharIteratorEx;
import com.oneuse.core.NumberRange;

public class NumberRangeParser {
    private final NumberParser numberParser = new NumberParser();
    private final CharTokenParser dashParser = CharTokenParser.DASH_PARSER;

    public NumberRange parse(CharIterator chars) {
        int begin = parseNumber(chars);

        if (!chars.isEnd() && parseDash(chars)) {
            CharIteratorEx.skipSpaces(chars);
            int end = parseNumber(chars);
            return new NumberRange(begin, end);
        }

        return new NumberRange(begin, begin);
    }

    private int parseNumber(CharIterator chars) {
        return numberParser.parse(chars);
    }

    private boolean parseDash(CharIterator chars) {
        CharIteratorEx.skipSpaces(chars);
        return dashParser.parse(chars);
    }
}
