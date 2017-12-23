package com.oneuse.dainbow.books.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import({
        PersistenceConfig.class,
        RepositoryConfig.class,
        WebConfig.class,
        BooksConfiguration.class
})
public class ApplicationConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    CorsRegistryConfigurer corsRegistryConfigurer() {
        return new CorsRegistryConfigurer();
    }
}
