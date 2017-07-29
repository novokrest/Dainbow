package com.oneuse.core;

import java.util.Random;

public class ByteArrayEx {
    private ByteArrayEx() {}

    public static byte[] generate(int size) {
        byte[] bytes = new byte[size];

        Random random = new Random();
        random.nextBytes(bytes);

        return bytes;
    }
}
