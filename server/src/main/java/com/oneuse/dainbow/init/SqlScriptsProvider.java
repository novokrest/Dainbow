package com.oneuse.dainbow.init;

import org.springframework.stereotype.Component;

@Deprecated
@Component
public class SqlScriptsProvider {
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

    public String[] updateTablesScripts() {
        return new String[] {
                "INSERT INTO Book (Id, Title, Author, TotalPages) " +
                        "         VALUES (1,\n" +
                        "                 \"Programming in Scala, Second Edition - 2010\",\n" +
                        "                 \"Odersky M., Spoon L., Venners B.\",\n" +
                        "                 883)",
                "INSERT INTO Book (Id, Title, Author, TotalPages) VALUES (2,\n" +
                        "                 \"Thinking in Java\",\n" +
                        "                 \"Bruce Eckel\",\n" +
                        "                 1079)",
                "INSERT INTO Book (Id, Title, Author, TotalPages) VALUES (3,\n" +
                        "                 \"C# in Depth, Third Edition\",\n" +
                        "                 \"Jon Skeet\",\n" +
                        "                 614)\n",
                "INSERT INTO Book (Id, Title, Author, TotalPages) VALUES (4,\n" +
                        "                 \"Pro ASP.NET MVC 5\",\n" +
                        "                 \"Adam Freeman\",\n" +
                        "                 812)\n",
                "INSERT INTO Book (Id, Title, Author, TotalPages) VALUES (5,\n" +
                        "                 \"Pro Git, 2nd edition\",\n" +
                        "                 \"Scott Chacon, Ben Straub\",\n" +
                        "                 574)",

                "INSERT INTO BookPages (BookId, BeginPage, EndPage) VALUES (1, 1, 10)",
                "INSERT INTO BookPages (BookId, BeginPage, EndPage) VALUES (2, 20, 30)",
                "INSERT INTO BookPages (BookId, BeginPage, EndPage) VALUES (2, 43, 58)",

                "INSERT INTO ReadHistory VALUES (1, '2016-12-15', '12:01:02', '13:04:02', 1, 10)",
                "INSERT INTO ReadHistory VALUES (2, '2016-12-16', '14:10:30', '15:11:20', 20, 30)",
                "INSERT INTO ReadHistory VALUES (2, '2016-12-17', '15:10:30', '15:40:20', 43, 58)",
        };
    }
}
