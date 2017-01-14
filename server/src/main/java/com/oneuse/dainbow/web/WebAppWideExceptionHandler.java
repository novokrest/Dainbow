package com.oneuse.dainbow.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebAppWideExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String runtimeExceptionHandler(Exception ex, Model model) {
        model.addAttribute("exception", ex);
        return "error";
    }
}
