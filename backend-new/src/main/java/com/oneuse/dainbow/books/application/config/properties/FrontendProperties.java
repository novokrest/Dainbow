package com.oneuse.dainbow.books.application.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application-${spring.profiles.active}.properties")
@ConfigurationProperties(prefix = "frontend")
public class FrontendProperties {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
