package com.oneuse.dainbow.config;

import com.oneuse.dainbow.BookService;
import com.oneuse.dainbow.storage.ReadHistoryRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Profile("test")
@PropertySource("classpath:test.properties")
public class TestConfig {

    @Bean
    public BookService bookService() {
        return Mockito.mock(BookService.class);
    }

    @Bean
    public ReadHistoryRepository readHistoryRepository() {
        return Mockito.mock(ReadHistoryRepository.class);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
