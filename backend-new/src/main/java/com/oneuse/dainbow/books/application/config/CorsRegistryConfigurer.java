package com.oneuse.dainbow.books.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.concurrent.TimeUnit;

public class CorsRegistryConfigurer {

    @Value("${frontend.server.url}")
    private String frontendServerUrl;

    public void configureCors(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins(frontendServerUrl)
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
