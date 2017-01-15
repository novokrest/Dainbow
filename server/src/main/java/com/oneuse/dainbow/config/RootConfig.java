package com.oneuse.dainbow.config;


import com.oneuse.dainbow.RootPackageMarker;
import com.oneuse.dainbow.init.DatabaseConnectionProperties;
import com.oneuse.dainbow.init.DatabaseConnectionPropertiesProvider;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackageClasses = RootPackageMarker.class,
               excludeFilters = {
                       @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
                       @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.oneuse\\.dainbow\\.web\\..*"),
                       @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
               })
public class RootConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource(DatabaseConnectionPropertiesProvider dbConnectionPropertiesProvider) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        DatabaseConnectionProperties dbConnectionProperties = dbConnectionPropertiesProvider.getDatabaseConnectionProperties();
        dataSource.setDriverClassName(dbConnectionProperties.getDriverClassName());
        dataSource.setUrl(dbConnectionProperties.getUrl());
        dataSource.setUsername(dbConnectionProperties.getUserName());
        dataSource.setPassword(dbConnectionProperties.getPassword());
        return dataSource;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("locale/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public LobHandler lobHandler() {
        return new DefaultLobHandler();
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
