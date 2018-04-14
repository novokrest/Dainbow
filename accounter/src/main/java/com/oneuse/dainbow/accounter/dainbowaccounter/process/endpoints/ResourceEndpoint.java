package com.oneuse.dainbow.accounter.dainbowaccounter.process.endpoints;

import javax.annotation.Nonnull;

public enum ResourceEndpoint {

    WEBJARS("/webjars/**"),

    CSS("/css/**")

    ;

    private final String path;

    ResourceEndpoint(String path) {
        this.path = path;
    }

    @Nonnull
    public String getPath() {
        return path;
    }
}
