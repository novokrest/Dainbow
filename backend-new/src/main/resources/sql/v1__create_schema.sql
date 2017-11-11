CREATE TABLE book
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100),
    pages_count INT,
    type INT
);

CREATE TABLE book_read_history
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    begin_page INT NOT NULL,
    end_page INT NOT NULL,
    begin_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY fk_book_id(book_id) REFERENCES book(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE book_read_progress
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    read_pages_count INT,
    total_pages_count INT,
    FOREIGN KEY fk_book_id(book_id) REFERENCES book(id) ON DELETE CASCADE ON UPDATE CASCADE
);