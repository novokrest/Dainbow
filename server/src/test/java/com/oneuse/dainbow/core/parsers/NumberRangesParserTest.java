package com.oneuse.dainbow.core.parsers;

import com.oneuse.core.CharIterator;
import com.oneuse.core.NumberRange;
import com.oneuse.core.parsers.NumberRangesParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NumberRangesParserTest {

    @Test
    public void testParseOneNumberRange() {
        List<NumberRange> expected = new ArrayList<NumberRange>(){{
            add(new NumberRange(1, 10));
        }};

        testSuccessfulParseNumberRanges("1-10", expected);
        testSuccessfulParseNumberRanges("1-  10", expected);
        testSuccessfulParseNumberRanges("1  -  10", expected);
        testSuccessfulParseNumberRanges("  1  -  10  ", expected);
        testSuccessfulParseNumberRanges("  1  -  10", expected);
        testSuccessfulParseNumberRanges("1  -  10  ", expected);
        testSuccessfulParseNumberRanges("  1-10  ", expected);
    }

    @Test
    public void testParseMultipleNumberRanges() {
        List<NumberRange> expected = new ArrayList<NumberRange>(){{
            add(new NumberRange(1, 10));
            add(new NumberRange(55, 55));
            add(new NumberRange(20, 30));
            add(new NumberRange(132, 145));
        }};

        testSuccessfulParseNumberRanges("1-10,55,20-30,132-145", expected);
        testSuccessfulParseNumberRanges("  1-10  , 55,  20-30 , 132-145   ", expected);
        testSuccessfulParseNumberRanges("  1 - 10  ,  55,20-   30 , 132 -  145   ", expected);
    }

    @Test
    public void testParseOneNumber() {
        List<NumberRange> expected = new ArrayList<NumberRange>(){{
           add(new NumberRange(55, 55));
        }};

        testSuccessfulParseNumberRanges("55", expected);
    }

    private void testSuccessfulParseNumberRanges(String text, List<NumberRange> expected) {
        CharIterator chars = new CharIterator(text);
        NumberRangesParser parser = new NumberRangesParser();

        List<NumberRange> numberRanges = parser.parse(chars);

        Assert.assertArrayEquals(expected.toArray(), numberRanges.toArray());
    }
}
