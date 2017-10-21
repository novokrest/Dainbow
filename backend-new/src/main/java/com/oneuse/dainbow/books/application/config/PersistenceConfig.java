package com.oneuse.dainbow.books.application.config;

import com.oneuse.dainbow.books.application.config.properties.DatabaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
@Import(DatabaseProperties.class)
public class PersistenceConfig {

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    DataSource dataSource(DatabaseProperties dbProperties) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(dbProperties.getUrl());
        dataSource.setUsername(dbProperties.getUsername());
        dataSource.setPassword(dbProperties.getPassword());

        return dataSource;
    }
}
