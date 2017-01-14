package com.oneuse.core.parsers;

import com.oneuse.core.CharIterator;
import com.oneuse.core.CharIteratorEx;
import com.oneuse.core.NumberRange;

import java.util.ArrayList;
import java.util.List;


public class NumberRangesParser {
    private final NumberRangeParser numberRangeParser = new NumberRangeParser();
    private final CharTokenParser commaParser = CharTokenParser.COMMA_PARSER;

    public List<NumberRange> parse(CharIterator chars) {
        List<NumberRange> numberRanges = new ArrayList<>();

        numberRanges.add(parseNumberRange(chars));
        while (!chars.isEnd()) {
            parseComma(chars);
            numberRanges.add(parseNumberRange(chars));
        }

        return numberRanges;
    }

    private NumberRange parseNumberRange(CharIterator chars) {
        CharIteratorEx.skipSpaces(chars);
        NumberRange numberRange = numberRangeParser.parse(chars);
        CharIteratorEx.skipSpaces(chars);
        return numberRange;
    }

    private void parseComma(CharIterator chars) {
        commaParser.parse(chars);
    }
}
