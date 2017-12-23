package com.oneuse.dainbow.books.application.process.books;

import com.oneuse.dainbow.books.application.process.books.add.AddBookCommand;
import com.oneuse.dainbow.books.application.process.books.add.AddBookRequest;
import com.oneuse.dainbow.books.application.process.books.add.AddBookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;
import java.util.Objects;

@RestController
@RequestMapping(
        path = "/api/v2/read/books",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class BooksController {

    private final AddBookCommand addBookCommand;

    public BooksController(@Nonnull AddBookCommand addBookCommand) {
        this.addBookCommand = Objects.requireNonNull(addBookCommand);
    }

    @RequestMapping("/add")
    public ResponseEntity<AddBookResponse> addBook(@RequestBody AddBookRequest request) {
        return ResponseEntity.ok(addBookCommand.execute(request));
    }
}
