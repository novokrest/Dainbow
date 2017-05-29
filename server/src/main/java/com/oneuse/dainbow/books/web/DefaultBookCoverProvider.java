package com.oneuse.dainbow.books.web;

import com.oneuse.dainbow.books.BookCoverProvider;
import com.oneuse.dainbow.books.image.Image;
import com.oneuse.dainbow.books.image.ImageType;
import com.oneuse.dainbow.books.storage.JdbcBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

@Component
public class DefaultBookCoverProvider implements BookCoverProvider {
    private final JdbcBookRepository bookRepository;
    private final ServletContext context;

    @Autowired
    public DefaultBookCoverProvider(JdbcBookRepository bookRepository, ServletContext context) {
        this.bookRepository = bookRepository;
        this.context = context;
    }

    @Override
    public Image findCover(long bookId) {
        Image cover = bookRepository.findOne(bookId).getCoverImage();
        return cover;
    }

    private Image loadDefaultCover() {
        try {
            ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
            String root = new File(".").getAbsolutePath();
            File bookCoverImgFile = new File(context.getRealPath("resources/img/book-cover.png"));
            BufferedImage bookCoverImg = ImageIO.read(bookCoverImgFile);
            ImageIO.write(bookCoverImg, "png", imgOutputStream);
            byte[] imgBytes = imgOutputStream.toByteArray();
            return new Image(ImageType.PNG, imgBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
