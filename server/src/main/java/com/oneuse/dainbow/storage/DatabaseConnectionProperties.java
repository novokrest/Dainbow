package com.oneuse.dainbow.storage;


public class DatabaseConnectionProperties {
    private final String driverClassName;
    private final String url;
    private final String userName;
    private final String password;

    public DatabaseConnectionProperties(String driverClassName, String url, String userName, String password) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
