package com.oneuse.dainbow.books.application.config;

import com.oneuse.dainbow.books.application.config.properties.DatabaseProperties;
import com.oneuse.dainbow.books.application.dao.BookDao;
import com.oneuse.dainbow.books.application.dao.BookHistoryDao;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDSLContext;
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

    @Bean
    BookDao bookDao(DSLContext dslContext) {
        return new BookDao(dslContext);
    }

    @Bean
    BookHistoryDao bookHistoryDao(DSLContext dslContext) {
        return new BookHistoryDao(dslContext);
    }

    @Bean
    DSLContext dslContext(DataSource dataSource) {
        return DSL.using(dataSource, SQLDialect.MYSQL);
    }
}
