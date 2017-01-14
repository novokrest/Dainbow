package com.oneuse.dainbow.storage;

import com.oneuse.core.time.DayPeriod;
import com.oneuse.dainbow.BookPages;
import com.oneuse.dainbow.BookPagesCreator;
import com.oneuse.dainbow.ReadActivity;
import com.oneuse.dainbow.config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalTime;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
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
