package com.oneuse.dainbow.books.application.process.books.add;

import com.oneuse.dainbow.books.application.entity.BookEntity;
import com.oneuse.dainbow.books.application.entity.BookReadActivityEntity;
import com.oneuse.dainbow.books.application.process.dto.ReadActivity;
import com.oneuse.dainbow.books.application.services.BookService;

import java.util.List;
import java.util.stream.Collectors;

public class AddBookCommand {

    private final BookService bookService;

    public AddBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    public AddBookResponse execute(AddBookRequest request) {
        BookEntity book = buildBook(request);
        List<BookReadActivityEntity> readActivities = buildReadActivities(request.getReadActivities());
        int bookId = bookService.addBook(book, readActivities);
        return new AddBookResponse(bookId);
    }

    private static BookEntity buildBook(AddBookRequest request) {
        return BookEntity.builder()
                .withTitle(request.getTitle())
                .withAuthor(request.getAuthor())
                .withTotalPagesCount(request.getTotalPagesCount())
                .build();
    }

    private List<BookReadActivityEntity> buildReadActivities(List<ReadActivity> readActivities) {
        return readActivities.stream().map(readActivity ->
                BookReadActivityEntity.builder()
                        .withBeginPage(readActivity.getBeginPage())
                        .withEndPage(readActivity.getEndPage())
                        .build())
                .collect(Collectors.toList());
    }
}
