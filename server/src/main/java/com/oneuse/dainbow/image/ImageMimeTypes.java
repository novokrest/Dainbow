package com.oneuse.dainbow.image;

import java.util.HashMap;
import java.util.Map;

public class ImageMimeTypes {
    private static final ImageMimeTypePair[] IMAGE_MIME_TYPE_PAIRS = new ImageMimeTypePair[] {
            new ImageMimeTypePair(ImageType.JPEG, "image/jpeg"),
            new ImageMimeTypePair(ImageType.PNG, "image/png")
    };

    private static final Map<ImageType, String> IMAGE_TYPE_TO_MIME_TYPE_MAP = new HashMap<>();
    private static final Map<String, ImageType> MIME_TYPE_TO_IMAGE_TYPE_MAP = new HashMap<>();

    static {
        for (ImageMimeTypePair pair : IMAGE_MIME_TYPE_PAIRS) {
            IMAGE_TYPE_TO_MIME_TYPE_MAP.put(pair.getImageType(), pair.getMimeType());
            MIME_TYPE_TO_IMAGE_TYPE_MAP.put(pair.getMimeType(), pair.getImageType());
        }
    }

    public static ImageType toImageType(String mimeType) {
        return MIME_TYPE_TO_IMAGE_TYPE_MAP.get(mimeType);
    }

    public static String toMimeType(ImageType imageType) {
        return IMAGE_TYPE_TO_MIME_TYPE_MAP.get(imageType);
    }

    private static class ImageMimeTypePair {
        private final ImageType imageType;
        private final String mimeType;

        public ImageMimeTypePair(ImageType imageType, String mimeType) {
            this.imageType = imageType;
            this.mimeType = mimeType;
        }

        public ImageType getImageType() {
            return imageType;
        }

        public String getMimeType() {
            return mimeType;
        }
    }
}
