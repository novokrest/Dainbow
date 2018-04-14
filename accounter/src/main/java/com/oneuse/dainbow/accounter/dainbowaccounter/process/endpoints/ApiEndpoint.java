package com.oneuse.dainbow.accounter.dainbowaccounter.process.endpoints;

import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;

public enum ApiEndpoint {

    SIGN_IN("/api/signIn"),

    SIGN_UP("/api/signUp")

    ;

    private final String path;

    ApiEndpoint(@Nonnull String path) {
        this.path = requireNonNull(path);
    }

    @Nonnull
    public String getPath() {
        return path;
    }
}
