package com.oneuse.dainbow.books;

public class DomainObject {
    private final long id;

    public DomainObject(long id) {
        this.id = id;
    }

    public final long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof DomainObject)) return false;

        DomainObject other = (DomainObject) obj;

        return this.id == other.id;
    }
}
