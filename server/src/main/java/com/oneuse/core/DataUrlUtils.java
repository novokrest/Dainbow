package com.oneuse.core;

import com.oneuse.core.parsers.DataUrlParser;
import com.oneuse.dainbow.image.Image;
import com.oneuse.dainbow.image.ImageMimeTypes;
import com.oneuse.dainbow.image.ImageType;

import java.util.Base64;

public class DataUrlUtils {
    private DataUrlUtils() {}

    public static String encode(Image image) {
        return encode(image.getType(), image.getData());
    }

    public static String encode(ImageType imageType, byte[] data) {
        return String.format("data:%s;base64,%s",
                             ImageMimeTypes.toMimeType(imageType),
                             Base64.getEncoder().encodeToString(data));
    }

    public static Image decode(String dataUrl) {
        DataUrlParser parser = new DataUrlParser(dataUrl);
        if (parser.parse()) {
            return new Image(parser.getImageType(), parser.getData());
        }
        return null;
    }
}
