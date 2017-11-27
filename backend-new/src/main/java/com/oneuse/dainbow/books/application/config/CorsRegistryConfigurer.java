package com.oneuse.dainbow.books.application.config;

import com.oneuse.dainbow.books.application.config.properties.FrontendProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.concurrent.TimeUnit;

public class CorsRegistryConfigurer {

    @Autowired
    FrontendProperties frontendProperties;

    public void configureCors(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins(frontendProperties.getUrls().toArray(new String[0]))
                .allowedMethods(
                        "GET",
                        "POST",
                        "DELETE"
                )
                .allowedHeaders(
                        "Content-Type",
                        "X-Requested-With",
                        "accept",
                        "Origin",
                        "Access-Control-Request-Method",
                        "Access-Control-Request-Headers"
                )
                .exposedHeaders(
                        "Access-Control-Allow-Origin",
                        "Access-Control-Allow-Credentials"
                )
                .allowCredentials(true)
                .maxAge(TimeUnit.DAYS.toSeconds(1));
    }
}
