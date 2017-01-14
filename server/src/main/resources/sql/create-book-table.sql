USE BooksDev;

DROP TABLE IF EXISTS Book;
CREATE TABLE Book (Id INT NOT NULL, Title VARCHAR(100), Author VARCHAR(100), TotalPages INT,
                   PRIMARY KEY(Id));
CREATE TABLE BookPages (BookId INT NOT NULL, BeginPage INT NOT NULL, EndPage INT NOT NULL,
                        FOREIGN KEY FK_Book(BookId) REFERENCES Book(Id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE);

INSERT INTO book VALUES (1,
                         "Programming in Scala, Second Edition - 2010",
                         "Odersky M., Spoon L., Venners B.",
                         883);
INSERT INTO book VALUES (2,
                         "Thinking in Java",
                         "Bruce Eckel",
                         1079);
INSERT INTO book VALUES (3,
                         "C# in Depth, Third Edition",
                         "Jon Skeet",
                         614);
INSERT INTO book VALUES (4,
                         "Pro ASP.NET MVC 5",
                         "Adam Freeman",
                         812);
INSERT INTO book VALUES (5,
                         "Pro Git, 2nd edition",
                         "Scott Chacon, Ben Straub",
                         574);