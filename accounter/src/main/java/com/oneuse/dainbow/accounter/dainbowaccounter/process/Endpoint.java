package com.oneuse.dainbow.accounter.dainbowaccounter.process;

import javax.annotation.Nonnull;

public enum Endpoint {

    ROOT("/"),

    LOGIN("/login"),

    UNAUTHORIZED("/unauthorized"),

    OAUTH_TOKEN("/oauth/token")

    ;

    private final String path;

    Endpoint(String path) {
        this.path = path;
    }

    @Nonnull
    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Endpoint{" +
                "path=" + path +
                '}';
    }
}
