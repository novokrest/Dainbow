package com.oneuse.dainbow.books.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final String HOME_PAGE = "index";

    @GetMapping(value = "/")
    public String index() {
        return HOME_PAGE;
    }
}
