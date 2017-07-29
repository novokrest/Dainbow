package com.oneuse.core;

//[ begin; end ]
public class NumberRange {
    private final int begin;
    private final int end;

    public NumberRange(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof NumberRange)) return false;

        NumberRange other = (NumberRange) obj;
        return this.begin == other.begin && this.end == other.end;
    }
}
