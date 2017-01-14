package com.oneuse.dainbow;

import com.oneuse.dainbow.image.Image;
import com.oneuse.dainbow.image.ImageType;

import java.util.ArrayList;
import java.util.List;

public class BookUtils {
    public static List<Book> createBooks(int count) {
        List<Book> books = new ArrayList<>(count);

        for (int i = 0; i < count; ++i) {
            books.add(createBook(i));
        }

        return books;
    }

    public static Book createBook(long bookId) {
        int pagesCount = (int)(Math.random() * 500 + 500);
        BookPagesImpl pages = createRandomBookPages(pagesCount);
        return new Book(bookId,
                        "Book #" + bookId,
                        "Author #" + bookId,
                        pagesCount,
                        pages,
                        defaultCoverImage());
    }

    public static Image defaultCoverImage() {
        return new Image(ImageType.JPEG, new byte[] {1, 2, 3});
    }

    public static BookPagesImpl createRandomBookPages(int pagesCount) {
        BookPagesImpl bookPages = new BookPagesImpl();
        bookPages.addPages(new PageRange(1, (int)(pagesCount * Math.random())));
        return bookPages;
    }
}
