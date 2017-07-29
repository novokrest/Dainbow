package com.oneuse.dainbow.books.storage;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.lob.LobHandler;

public abstract class JdbcRepository {
    private static final String SELECT_ENTRIES_COUNT_FORMAT = "SELECT COUNT(*) FROM %s";
    private static final String SELECT_MAX_ID_FORMAT = "SELECT MAX(Id) FROM %s";

    protected final JdbcOperations jdbcOperations;
    protected final LobHandler lobHandler;
    protected final String tableName;

    protected JdbcRepository(JdbcOperations jdbcOperations, LobHandler lobHandler, String tableName) {
        this.jdbcOperations = jdbcOperations;
        this.lobHandler = lobHandler;
        this.tableName = tableName;
    }

    public int count() {
        return jdbcOperations.query(formatWithTableName(SELECT_ENTRIES_COUNT_FORMAT), resultSet -> (Integer) resultSet.getInt(1), tableName);
    }

    protected long findAvailableId() {
        return jdbcOperations.queryForObject(formatWithTableName(SELECT_MAX_ID_FORMAT),
                                             (resultSet, rowNum) -> resultSet.getLong(1) + 1);
    }

    protected String formatWithTableName(String query) {
        return String.format(query, tableName);
    }
}
