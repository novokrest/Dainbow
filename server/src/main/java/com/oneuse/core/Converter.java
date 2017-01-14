package com.oneuse.core;

public interface Converter<T, R> {
    R convert(T t);
}
