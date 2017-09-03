package com.oneuse.dainbow.books.config;

import com.oneuse.dainbow.books.properties.DatabaseProperties;
import com.oneuse.dainbow.books.storage.DatabaseConnectionProperties;
import com.oneuse.dainbow.books.storage.DatabaseConnectionPropertiesProvider;
import com.oneuse.dainbow.books.storage.PersistencePackageMarker;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
@Import(DatabaseProperties.class)
public class PersistenceConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource(DatabaseProperties databaseProperties) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseProperties.getDriverClass());
        dataSource.setUrl(databaseProperties.getUrl());
        dataSource.setUsername(databaseProperties.getUser());
        dataSource.setPassword(databaseProperties.getPassword());
        return dataSource;
    }

    @Bean
    public LobHandler lobHandler() {
        return new DefaultLobHandler();
    }
}
