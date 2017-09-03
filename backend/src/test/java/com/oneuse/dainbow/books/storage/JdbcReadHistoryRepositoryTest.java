package com.oneuse.dainbow.books.storage;

import com.oneuse.core.time.DayPeriod;
import com.oneuse.dainbow.books.config.ApplicationConfig;
import com.oneuse.dainbow.books.domain.BookPages;
import com.oneuse.dainbow.books.services.BookPagesCreator;
import com.oneuse.dainbow.books.domain.ReadActivity;
import com.oneuse.dainbow.books.config.PersistenceConfig;
import com.oneuse.dainbow.books.config.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.time.LocalTime;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
@ActiveProfiles({"test"})
public class JdbcReadHistoryRepositoryTest {
    @Autowired
    private JdbcReadHistoryRepository repository;

    @Test
    public void testSuccessfulAddingOfReadActivity() {
        ReadActivity readActivity = createReadActivity();
        repository.logReadActivity(1, readActivity);
    }

    private ReadActivity createReadActivity() {
        BookPages bookPages = BookPagesCreator.create(1, 10);
        DayPeriod dayPeriod = new DayPeriod(LocalDate.parse("2017-01-14"),
                                            LocalTime.parse("12:00:00"),
                                            LocalTime.parse("13:00:00"));
        return new ReadActivity(bookPages, dayPeriod);
    }
}
