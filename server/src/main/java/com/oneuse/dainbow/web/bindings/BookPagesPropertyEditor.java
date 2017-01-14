package com.oneuse.dainbow.web.bindings;

import com.oneuse.dainbow.BookPagesImpl;
import com.oneuse.dainbow.BookPagesParser;

import java.beans.PropertyEditorSupport;

public class BookPagesPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) {
        BookPagesImpl bookPages = BookPagesParser.parse(text);
        setValue(bookPages);
    }
}
