package com.oneuse.dainbow.books.services;


import com.oneuse.dainbow.books.image.Image;

public interface BookCoverProvider {
    Image findCover(long bookId);
}
