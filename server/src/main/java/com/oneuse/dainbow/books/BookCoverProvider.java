package com.oneuse.dainbow.books;


import com.oneuse.dainbow.books.image.Image;

public interface BookCoverProvider {
    Image findCover(long bookId);
}
