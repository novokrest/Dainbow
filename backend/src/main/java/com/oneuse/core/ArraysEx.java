package com.oneuse.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArraysEx {
    public static <T> Set<T> asSet(T[] array) {
        return new HashSet<>(Arrays.asList(array));
    }
}
