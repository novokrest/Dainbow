package com.oneuse.dainbow.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class BookDatabaseInitializer {
    private final JdbcOperations jdbcOperations;
    private final SqlScriptsProvider sqlScriptsProvider;

    @Autowired
    public BookDatabaseInitializer(JdbcOperations jdbcOperations, SqlScriptsProvider sqlScriptsProvider) {
        this.jdbcOperations = jdbcOperations;
        this.sqlScriptsProvider = sqlScriptsProvider;
    }

    public void initialize() {
        Arrays.stream(sqlScriptsProvider.createTablesScripts()).forEach(script -> jdbcOperations.execute(script));
        Arrays.stream(sqlScriptsProvider.updateTablesScripts()).forEach(script -> jdbcOperations.execute(script));
    }
}
