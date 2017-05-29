package com.oneuse.dainbow.books.initdb;

public interface SqlScriptsProvider {
    String[] createTablesScripts();
    String[] updateTablesScripts();
}
