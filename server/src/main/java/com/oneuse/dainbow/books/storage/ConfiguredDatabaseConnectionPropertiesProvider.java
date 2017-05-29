package com.oneuse.dainbow.books.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfiguredDatabaseConnectionPropertiesProvider implements DatabaseConnectionPropertiesProvider {
    @Value("${database.driver}")
    private String driverClassName;

    @Value("${database.url}")
    private String url;

    @Value("${database.user}")
    private String userName;

    @Value("${database.password}")
    private String password;

    @Override
    public DatabaseConnectionProperties getDatabaseConnectionProperties() {
        return new DatabaseConnectionProperties(driverClassName, url, userName, password);
    }
}
