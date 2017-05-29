package com.oneuse.dainbow.books.config;

import com.oneuse.dainbow.books.BookService;
import com.oneuse.dainbow.books.storage.ReadHistoryRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.oneuse\\.dainbow\\.BookService")
})
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
    public PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactory() {
        return new LocalValidatorFactoryBean();
    }
}
