package com.oneuse.dainbow.books.init;

public interface SqlScriptsProvider {
    String[] createTablesScripts();
    String[] updateTablesScripts();
}
