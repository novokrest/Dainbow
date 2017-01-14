package com.oneuse.dainbow.core;

import com.oneuse.core.CharIterator;
import org.junit.Assert;
import org.junit.Test;

public class CharIteratorTest {
    @Test
    public void testIteratorForEmptyString() {
        final String emptyString = "";
        CharIterator iterator = new CharIterator(emptyString);
        Assert.assertTrue(iterator.isEnd());
    }

    @Test
    public void testIteratorForOneCharacterString() {
        final String oneCharacterString = "A";
        testIteratorForNonEmptyString(oneCharacterString);
    }

    @Test
    public void testIteratorForMultiCharacterString() {
        final String multiCharacterString = "ABCDE12345_";
        testIteratorForNonEmptyString(multiCharacterString);
    }

    private void testIteratorForNonEmptyString(String nonEmptyString) {
        CharIterator iterator = new CharIterator(nonEmptyString);

        for (int i = 0, length = nonEmptyString.length(); i < length; ++i) {
            Assert.assertEquals(nonEmptyString.charAt(i), iterator.current());
            iterator.moveNext();
        }

        Assert.assertTrue(iterator.isEnd());
    }
}
