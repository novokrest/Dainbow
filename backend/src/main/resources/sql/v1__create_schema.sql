CREATE TABLE BOOK
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100),
    pages_count INT,
    type INT
);

CREATE TABLE BOOK_READ_HISTORY
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    begin_page INT NOT NULL,
    end_page INT NOT NULL,
    begin_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY fk_book_id(book_id) REFERENCES BOOK(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE BOOK_READ_PROGRESS
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    read_pages_count INT,
    total_pages_count INT,
    FOREIGN KEY fk_book_id(book_id) REFERENCES BOOK(id) ON DELETE CASCADE ON UPDATE CASCADE
);