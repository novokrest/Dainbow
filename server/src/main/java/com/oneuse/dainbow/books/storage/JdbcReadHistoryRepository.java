package com.oneuse.dainbow.books.storage;

import com.oneuse.core.time.DayPeriod;
import com.oneuse.dainbow.books.domain.BookPages;
import com.oneuse.dainbow.books.services.BookPagesCreator;
import com.oneuse.dainbow.books.domain.PageRange;
import com.oneuse.dainbow.books.domain.ReadActivity;
import com.oneuse.dainbow.books.domain.ReadHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Repository
public class JdbcReadHistoryRepository implements ReadHistoryRepository {
    private static final String SELECT_READ_PAGES_BY_BOOKID = "SELECT Day, BeginTime, EndTime, BeginPage, EndPage FROM ReadHistory WHERE bookId = ?";
    private static final String SELECT_READ_PAGES_BY_BOOKID_AND_DATE = "SELECT BeginTime, EndTime, BeginPage, EndPage " +
                                                                       "FROM ReadHistory " +
                                                                       "WHERE bookId = ? AND Day = ?";
    private static final String INSERT_READ_PAGES = "INSERT INTO ReadHistory (BookId, Day, BeginTime, EndTime, BeginPage, EndPage) VALUES (?, ?, ?, ?, ?, ?)";

    private final JdbcOperations jdbcOperations;

    @Autowired
    public JdbcReadHistoryRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public ReadHistory findReadHistory(long bookId) {
        return jdbcOperations.query(SELECT_READ_PAGES_BY_BOOKID, resultSet -> {
            return loadReadHistory(resultSet);
        }, bookId);
    }

    private ReadHistory loadReadHistory(ResultSet resultSet) throws SQLException {
        List<ReadActivity> readActivities = new ArrayList<>();

        while(resultSet.next()) {
            ReadActivity readActivity = loadReadActivity(resultSet);
            readActivities.add(readActivity);
        }

        return new ReadHistory(readActivities);
    }

    private ReadActivity loadReadActivity(ResultSet resultSet) throws SQLException {
        BookPages bookPages = loadBookPages(resultSet);
        DayPeriod dayPeriod = loadDayPeriod(resultSet);
        return new ReadActivity(bookPages, dayPeriod);
    }

    private BookPages loadBookPages(ResultSet resultSet) throws SQLException {
        int beginPage = resultSet.getInt("BeginPage");
        int endPage = resultSet.getInt("EndPage");
        return BookPagesCreator.create(beginPage, endPage);
    }

    private DayPeriod loadDayPeriod(ResultSet resultSet) throws SQLException {
        LocalDate day = resultSet.getDate("Day").toLocalDate();
        LocalTime beginTime = resultSet.getTime("BeginTime").toLocalTime();
        LocalTime endTime = resultSet.getTime("EndTime").toLocalTime();

        return new DayPeriod(day, beginTime, endTime);
    }

    @Override
    public void logReadActivity(long bookId, ReadActivity readActivity) {
        DayPeriod period = readActivity.getPeriod();
        for (PageRange pageRange : readActivity.getBookPages()) {
            jdbcOperations.update(INSERT_READ_PAGES,
                                  bookId,
                                  period.day(), period.timePeriod().getBegin(), period.timePeriod().getEnd(),
                                  pageRange.getBegin(), pageRange.getEnd());
        }
    }
}
