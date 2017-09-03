package com.oneuse.dainbow.books.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:database.properties")
public class DatabaseProperties {

    @Value("${database.driverClass}")
    private String driverClass;

    @Value("${database.url}")
    private String url;

    @Value("${database.user}")
    private String user;

    @Value("${database.password}")
    private String password;

    public String getDriverClass() {
        return driverClass;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
