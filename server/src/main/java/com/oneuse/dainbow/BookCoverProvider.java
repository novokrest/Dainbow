package com.oneuse.dainbow;


import com.oneuse.dainbow.image.Image;

public interface BookCoverProvider {
    Image findCover(long bookId);
}
