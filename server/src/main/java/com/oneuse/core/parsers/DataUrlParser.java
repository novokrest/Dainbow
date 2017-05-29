package com.oneuse.core.parsers;

import com.oneuse.dainbow.books.image.ImageMimeTypes;
import com.oneuse.dainbow.books.image.ImageType;
import org.springframework.util.Base64Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUrlParser {
    private static final Pattern DATA_URL_PATTERN = Pattern.compile("data:(?<mediaType>\\w+/\\w+)?(;base64)?,(?<data>[\\w\\d+/=]+)");

    private final String dataUrl;
    private ImageType imageType;
    private byte[] data;

    public DataUrlParser(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public byte[] getData() {
        return data;
    }

    public boolean parse() {
        Matcher matcher = DATA_URL_PATTERN.matcher(dataUrl);
        if (!matcher.matches()) {
            return false;
        }

        parseMimeType(matcher);
        parseData(matcher);
        return true;
    }

    private void parseMimeType(Matcher matcher) {
        String mimeType = matcher.group("mediaType");
        if (mimeType != null) {
            imageType = ImageMimeTypes.toImageType(mimeType);
        }
    }

    private void parseData(Matcher matcher) {
        data = Base64Utils.decodeFromString(matcher.group("data"));
    }
}
