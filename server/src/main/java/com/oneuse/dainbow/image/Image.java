package com.oneuse.dainbow.image;

import java.util.Arrays;

public class Image {
    private final ImageType type;
    private final byte[] data;

    public static Image empty(ImageType imageType) {
        return new Image(imageType, new byte[0]);
    }

    public Image(String mimeType, byte[] data) {
        this(ImageMimeTypes.toImageType(mimeType), data);
    }

    public Image(ImageType type, byte[] data) {
        this.type = type;
        this.data = data;
    }

    public ImageType getType() {
        return type;
    }

    public byte[] getData() {
        return data;
    }

    public String getMimeType() {
        return ImageMimeTypes.toMimeType(type);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (!(object instanceof Image)) return false;

        Image other = (Image) object;
        return type == other.type && Arrays.equals(data, other.data);
    }
}
