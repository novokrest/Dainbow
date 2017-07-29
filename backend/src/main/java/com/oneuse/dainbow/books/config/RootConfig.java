package com.oneuse.dainbow.books.config;


import com.oneuse.dainbow.books.RootPackageMarker;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan(basePackageClasses = RootPackageMarker.class,
               excludeFilters = {
                       @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
                       @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class),
                       @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.oneuse\\.dainbow\\.web\\..*"),
                       @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.oneuse\\.dainbow\\.storage\\..*")
               })
public class RootConfig {
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("locale/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Configuration
    @Profile("prod")
    @PropertySource("classpath:production.properties")
    public static class ProdConfig { }

    @Configuration
    @Profile("dev")
    @PropertySource("classpath:development.properties")
    public static class DevConfig { }

    @Configuration
    @Profile("test")
    @PropertySource("classpath:test.properties")
    public static class TestConfig { }
}
