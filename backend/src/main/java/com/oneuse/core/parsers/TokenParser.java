package com.oneuse.core.parsers;

import com.oneuse.core.CharIterator;

public interface TokenParser {
    boolean parse(CharIterator iterator);
    Token getToken();
}
