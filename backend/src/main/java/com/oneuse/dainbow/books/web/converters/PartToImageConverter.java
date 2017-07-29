package com.oneuse.dainbow.books.web.converters;

import com.oneuse.dainbow.books.image.Image;
import com.oneuse.dainbow.books.image.ImageMimeTypes;
import com.oneuse.dainbow.books.image.ImageType;

import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PartToImageConverter {
    private final Part part;

    public static Image convert(Part part) throws IOException {
        return new PartToImageConverter(part).convert();
    }

    private PartToImageConverter(Part part) {
        this.part = part;
    }

    private Image convert() throws IOException {
        return new Image(getType(), getData());
    }

    private byte[] getData() throws IOException {
        InputStream is = part.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        return buffer.toByteArray();
    }

    private ImageType getType() {
        return ImageMimeTypes.toImageType(part.getContentType());
    }
}
