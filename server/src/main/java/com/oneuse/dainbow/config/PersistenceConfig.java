package com.oneuse.dainbow.config;

import com.oneuse.dainbow.storage.DatabaseConnectionProperties;
import com.oneuse.dainbow.storage.DatabaseConnectionPropertiesProvider;
import com.oneuse.dainbow.storage.PersistencePackageMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = PersistencePackageMarker.class)
public class PersistenceConfig {
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
    public LobHandler lobHandler() {
        return new DefaultLobHandler();
    }
}
