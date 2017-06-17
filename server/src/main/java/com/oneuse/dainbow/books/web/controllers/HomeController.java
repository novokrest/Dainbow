package com.oneuse.dainbow.books.web.controllers;

import com.oneuse.dainbow.books.dto.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    private static final String HOME_PAGE = "index";
    private static final String ADD_BOOK_PAGE = "add-book";

    @GetMapping
    public String index() {
        return HOME_PAGE;
    }

    @GetMapping(value = "/add")
    public String addBook() {
        return ADD_BOOK_PAGE;
    }

    @PostMapping(value = "/add")
    public void addBook(@RequestBody Book book) {
        log.info("Receive request for add book: {}", book);
    }
}
