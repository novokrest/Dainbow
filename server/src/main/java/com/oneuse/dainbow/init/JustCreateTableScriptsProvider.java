package com.oneuse.dainbow.init;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class JustCreateTableScriptsProvider implements SqlScriptsProvider {
    @Override
    public String[] createTablesScripts() {
        return new String[] {
                "DROP TABLE IF EXISTS ReadHistory",
                "DROP TABLE IF EXISTS BookPages",
                "DROP TABLE IF EXISTS Cover",
                "DROP TABLE IF EXISTS Book",

                "CREATE TABLE Book (Id INT NOT NULL, " +
                        "           Title VARCHAR(100), Author VARCHAR(100), TotalPages INT," +
                        "           PRIMARY KEY(Id))",
                "CREATE TABLE Cover(BookId INT NOT NULL, " +
                        "           Image MEDIUMBLOB NOT NULL, Type INT NOT NULL, " +
                        "           FOREIGN KEY FK_Book(BookId) REFERENCES Book(Id) " +
                        "           ON DELETE CASCADE" +
                        "           ON UPDATE CASCADE)",
                "CREATE TABLE BookPages (BookId INT NOT NULL, " +
                        "                BeginPage INT NOT NULL, EndPage INT NOT NULL, " +
                        "                FOREIGN KEY FK_Book(BookId) REFERENCES Book(Id) " +
                        "                ON DELETE CASCADE" +
                        "                ON UPDATE CASCADE)",
                "CREATE TABLE ReadHistory (BookId INT NOT NULL, " +
                        "                  Day DATE NOT NULL, BeginTime TIME NOT NULL, EndTime TIME NOT NULL, " +
                        "                  BeginPage INT NOT NULL, EndPage INT NOT NULL, " +
                        "                  FOREIGN KEY FK_Book(BookId) REFERENCES Book(Id) " +
                        "                  ON DELETE CASCADE " +
                        "                  ON UPDATE CASCADE)"
        };
    }

    @Override
    public String[] updateTablesScripts() {
        return new String[0];
    }
}
