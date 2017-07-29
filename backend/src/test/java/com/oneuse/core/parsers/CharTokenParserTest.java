package com.oneuse.core.parsers;

import com.oneuse.core.CharIterator;
import com.oneuse.core.parsers.CharTokenParser;
import org.junit.Assert;
import org.junit.Test;

public class CharTokenParserTest {
    private static final char DOT_CHARACTER = '.';
    private static final char COMMA_CHARACTER = ',';

    @Test
    public void testSuccessfulParseSpecificCharacter() {
        CharIterator chars = getChars(DOT_CHARACTER);
        CharTokenParser parser = new CharTokenParser(DOT_CHARACTER);

        Assert.assertTrue(parser.parse(chars));
        Assert.assertTrue(chars.isEnd());
    }

    @Test
    public void testFailedParseSpecificCharacter() {
        CharIterator chars = getChars(COMMA_CHARACTER);
        CharTokenParser parser = new CharTokenParser(DOT_CHARACTER);

        Assert.assertFalse(parser.parse(chars));
        Assert.assertFalse(chars.isEnd());
    }

    private CharIterator getChars(char... characters) {
        String text = new String(characters);
        return new CharIterator(text);
    }
}
