package com.oneuse.dainbow.init;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"dev", "test"})
public class DevSqlScriptsProvider extends JustCreateTableScriptsProvider {
    @Override
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
