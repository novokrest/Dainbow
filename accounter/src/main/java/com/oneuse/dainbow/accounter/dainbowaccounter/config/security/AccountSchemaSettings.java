package com.oneuse.dainbow.accounter.dainbowaccounter.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "account-schema")
@Configuration
public class AccountSchemaSettings {

    private String usersByUsername;

    private String authoritiesByUsername;

    public String getUsersByUsername() {
        return usersByUsername;
    }

    public void setUsersByUsername(String usersByUsername) {
        this.usersByUsername = usersByUsername;
    }

    public String getAuthoritiesByUsername() {
        return authoritiesByUsername;
    }

    public void setAuthoritiesByUsername(String authoritiesByUsername) {
        this.authoritiesByUsername = authoritiesByUsername;
    }
}
