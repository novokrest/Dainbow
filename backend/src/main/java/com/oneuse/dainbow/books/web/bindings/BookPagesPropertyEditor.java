package com.oneuse.dainbow.books.web.bindings;

import com.oneuse.dainbow.books.domain.BookPagesImpl;
import com.oneuse.dainbow.books.services.BookPagesParser;

import java.beans.PropertyEditorSupport;

public class BookPagesPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) {
        BookPagesImpl bookPages = BookPagesParser.parse(text);
        setValue(bookPages);
    }
}
