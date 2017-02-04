package com.oneuse.dainbow.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class WebAppWideExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String runtimeExceptionHandler(Exception ex, Model model, HttpServletResponse response) {
        model.addAttribute("exception", ex);
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return "error";
    }
}
