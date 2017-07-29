package com.oneuse.core;

public class Verifiers {
    public static void verify(boolean b) {
        if (!b) {
            throw new RuntimeException();
        }
    }

    public static void verify(boolean b, String message, Object... args) {
        if (!b) {
            throw new RuntimeException(String.format(message, args));
        }
    }
}
