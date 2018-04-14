package com.oneuse.dainbow.accounter.dainbowaccounter.process.endpoints;

import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Nonnull;

public enum PublicEndpoint {

    ROOT("/"),

    HOME("/home"),

    LOGIN("/login"),

    UNAUTHORIZED("/unauthorized"),

    OAUTH_TOKEN("/oauth/token")

    ;

    private final String path;

    PublicEndpoint(String path) {
        this.path = path;
    }

    @Nonnull
    public String getPath() {
        return path;
    }

    @Nonnull
    public static String toErrorPath(@Nonnull PublicEndpoint endpoint) {
        return UriComponentsBuilder.fromPath(endpoint.getPath()).queryParam("error", Boolean.TRUE).toUriString();
    }
}
