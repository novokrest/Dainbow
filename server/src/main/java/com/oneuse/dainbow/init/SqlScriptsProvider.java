package com.oneuse.dainbow.init;

public interface SqlScriptsProvider {
    String[] createTablesScripts();
    String[] updateTablesScripts();
}
