package com.oneuse.dainbow.books.web;

import com.oneuse.dainbow.books.BookPages;
import com.oneuse.dainbow.books.web.bindings.BookPagesPropertyEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;


@ControllerAdvice
public class WebAppWideBindingInitializer {
//    @InitBinder
//    public void initDateBinder(WebDataBinder binder) {
//        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
//        sdf.setLenient(true);
//        sdf.setTimeZone(TimeZone.getDefault());
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
//    }

    @InitBinder
    public void initBookPagesBinder(WebDataBinder binder) {
        binder.registerCustomEditor(BookPages.class, new BookPagesPropertyEditor());
    }
}
