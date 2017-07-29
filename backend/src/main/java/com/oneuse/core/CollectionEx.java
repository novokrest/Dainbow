package com.oneuse.core;

import java.util.*;

public class CollectionEx {
    public static <E> List<E> fromIterable(Iterable<E> iterable) {
        List<E> list = new ArrayList<>();
        for (E e : iterable) {
            list.add(e);
        }
        return list;
    }

    public static <E> Set<E> asSet(Collection<E> collection) {
        return new HashSet<>(collection);
    }
}
