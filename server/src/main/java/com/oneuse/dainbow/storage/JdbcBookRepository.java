package com.oneuse.dainbow.storage;

import com.oneuse.core.Verifiers;
import com.oneuse.dainbow.Book;
import com.oneuse.dainbow.BookPages;
import com.oneuse.dainbow.BookPagesImpl;
import com.oneuse.dainbow.PageRange;
import com.oneuse.dainbow.image.Image;
import com.oneuse.dainbow.image.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Repository
public class JdbcBookRepository extends JdbcRepository implements BookRepository {
    private static final String SELECT_ALL_BOOKS = "SELECT Id, Title, Author, TotalPages FROM book";
    private static final String SELECT_BOOKS_WITH_LIMIT = "SELECT Id, Title, Author, TotalPages FROM book LIMIT ?";
    private static final String SELECT_BOOK_BY_ID = "SELECT Id, Title, Author, TotalPages FROM book WHERE Id = ?";
    private static final String INSERT_BOOK = "INSERT INTO Book (Id, Title, Author, TotalPages) VALUES (?, ?, ?, ?)";

    private static final String SELECT_BOOK_PAGES_BY_BOOK_ID = "SELECT BeginPage, EndPage FROM BookPages WHERE BookId = ?";
    private static final String INSERT_BOOK_PAGES = "INSERT INTO BookPages VALUES (?, ?, ?)";

    private static final String SELECT_BOOK_COVER_BY_BOOK_ID = "SELECT Image, Type FROM Cover WHERE BookId = ?";
    private static final String INSERT_BOOK_COVER = "INSERT INTO Cover (BookId, Image, Type) VALUES (?, ?, ?)";

    private final Map<Long, Book> books = new HashMap<>();

    @Autowired
    public JdbcBookRepository(JdbcOperations jdbcOperations, LobHandler lobHandler) {
        super(jdbcOperations, lobHandler, "Book");
    }

    public List<Book> find(int count) {
        return jdbcOperations.query(SELECT_BOOKS_WITH_LIMIT, resultSet -> {
            int innerCount = count;
            List<Book> books = new ArrayList<>();
            while(resultSet.next() && innerCount-- > 0) {
                Book book = loadBook(resultSet);
                books.add(book);
            }
            Verifiers.verify(innerCount == 0,
                             "Failed to load required books count: loaded = %d, requested = %d", innerCount, count);
            return books;
        });
    }

    public List<Book> findAll() {
        return jdbcOperations.query(SELECT_ALL_BOOKS, resultSet -> {
           List<Book> books = new ArrayList<>();
           while(resultSet.next()) {
               Book book = loadBook(resultSet);
               books.add(book);
           }
           return books;
        });
    }

    public Book findOne(long bookId) {
        if (books.containsKey(bookId)) {
            return books.get(bookId);
        }

        return jdbcOperations.queryForObject(SELECT_BOOK_BY_ID, (resultSet, rowNum) -> {
            return loadBook(resultSet);
        }, bookId);
    }

    private BookPagesImpl findReadBookPages(long bookId) {
        return jdbcOperations.query(SELECT_BOOK_PAGES_BY_BOOK_ID, resultSet -> {
            return loadBookPages(resultSet);
        }, bookId);
    }

    private Book loadBook(ResultSet resultSet) throws SQLException {
        long bookId = resultSet.getLong("Id");
        if (books.containsKey(bookId)) {
            return books.get(bookId);
        }
        Book book = new Book(bookId,
                        resultSet.getString("Title"),
                        resultSet.getString("Author"),
                        resultSet.getInt("TotalPages"),
                        new LazyBookPages(bookId), loadCoverImage(bookId));
        books.put(bookId, book);
        return book;
    }

    private BookPagesImpl loadBookPages(ResultSet resultSet) throws SQLException {
        BookPagesImpl bookPages = new BookPagesImpl();
        while(resultSet.next()) {
            int beginPage = resultSet.getInt("BeginPage");
            int endPage = resultSet.getInt("EndPage");
            bookPages.addPages(new PageRange(beginPage, endPage));
        }
        return bookPages;
    }

    private Image loadCoverImage(long bookId) {
        return jdbcOperations.queryForObject(SELECT_BOOK_COVER_BY_BOOK_ID, (resultSet, i) ->  {
            byte[] data = lobHandler.getBlobAsBytes(resultSet, "Image");
            ImageType type = ImageType.values()[resultSet.getInt("Type")];
            return new Image(type, data);
        }, bookId);
    }


    public Book addBook(Book book) {
        long newBookId = findAvailableBookId();

        Book newBook = new Book(newBookId,
                                book.getTitle(), book.getAuthor(),
                                book.getTotalPagesCount(), book.getReadPages(),
                                book.getCoverImage());

        jdbcOperations.update(INSERT_BOOK,
                              newBook.getId(), newBook.getTitle(), newBook.getAuthor(),
                              newBook.getTotalPagesCount());
        books.put(newBookId, newBook);

        addBookPages(newBookId, newBook.getReadPages());
        addCoverImage(newBookId, newBook.getCoverImage());

        return newBook;
    }

    private long findAvailableBookId() {
        return findAvailableId();
    }

    public void addBookPages(long bookId, BookPages bookPages) {
        Book book = findOne(bookId);
        for (PageRange pageRange: bookPages) {
            jdbcOperations.update(INSERT_BOOK_PAGES, bookId, pageRange.getBegin(), pageRange.getEnd());
            book.addReadPages(pageRange);
        }
    }

    private void addCoverImage(long bookId, Image coverImage) {
        jdbcOperations.execute(INSERT_BOOK_COVER, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
            @Override
            protected void setValues(PreparedStatement preparedStatement, LobCreator lobCreator)
                    throws SQLException, DataAccessException {
                preparedStatement.setLong(1, bookId);
                lobCreator.setBlobAsBytes(preparedStatement, 2, coverImage.getData());
                preparedStatement.setLong(3, coverImage.getType().ordinal());
            }
        });
    }

    public class LazyBookPages implements BookPages {
        private final long bookId;
        private BookPagesImpl bookPages;

        public LazyBookPages(long bookId) {
            this.bookId = bookId;
        }

        @Override
        public Iterable<Integer> getPages() {
            loadBookPages();
            return bookPages.getPages();
        }

        @Override
        public int getPagesCount() {
            loadBookPages();
            return bookPages.getPagesCount();
        }

        @Override
        public void addPages(PageRange pageRange) {
            loadBookPages();
            bookPages.addPages(pageRange);
        }

        public void addPage(int pageNumber) {
            bookPages.addPage(pageNumber);
        }

        @Override
        public Iterator<PageRange> iterator() {
            loadBookPages();
            return bookPages.iterator();
        }

        private void loadBookPages() {
            if (bookPages == null) {
                bookPages = JdbcBookRepository.this.findReadBookPages(bookId);
            }
        }
    }

}
