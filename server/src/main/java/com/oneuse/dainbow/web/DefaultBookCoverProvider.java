package com.oneuse.dainbow.web;

import com.oneuse.dainbow.BookCoverProvider;
import com.oneuse.dainbow.image.Image;
import com.oneuse.dainbow.image.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

@Component
public class DefaultBookCoverProvider implements BookCoverProvider {
    private final ServletContext context;

    @Autowired
    public DefaultBookCoverProvider(ServletContext context) {
        this.context = context;
    }

    @Override
    public Image findCover(long bookId) {
        try {
            ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
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
