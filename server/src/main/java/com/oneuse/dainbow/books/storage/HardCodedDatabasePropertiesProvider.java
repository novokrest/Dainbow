package com.oneuse.dainbow.books.storage;

@Deprecated
//@Component
public class HardCodedDatabasePropertiesProvider implements DatabaseConnectionPropertiesProvider {

    @Override
    public DatabaseConnectionProperties getDatabaseConnectionProperties() {
        return new DatabaseConnectionProperties("com.mysql.cj.jdbc.Driver",
                                                "jdbc:mysql://localhost:3306/booktest1?useSSL=false&serverTimezone=Europe/Moscow",
                                                "root", "123");
    }
}
