package com.oneuse.dainbow.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebAppWideExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public String runtimeExceptionHandler() {
        return "error";
    }
}
