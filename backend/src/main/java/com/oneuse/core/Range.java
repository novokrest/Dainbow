package com.oneuse.core;

public class Range<T extends Comparable<T>> {
    private final T begin;
    private final T end;

    public Range(T begin, T end) {
        Verifiers.verify(begin.compareTo(end) <= 0, "Incorrect range: beginPage = %d, endPage = %d", begin, end);
        this.begin = begin;
        this.end = end;
    }

    public T getBegin() {
        return begin;
    }

    public T getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Range)) return false;

        Range other = (Range) obj;
        return this.begin.equals(other.begin) && this.end.equals(other.end);
    }

    @Override
    public int hashCode() {
        return begin.hashCode() * 7 + end.hashCode();
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", getBegin(), getEnd());
    }
}
