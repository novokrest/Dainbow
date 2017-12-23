package com.oneuse.dainbow.books.application.dao;

import com.oneuse.dainbow.books.application.entity.BookReadActivityEntity;
import com.oneuse.dainbow.books.application.jooq.Tables;
import com.oneuse.dainbow.books.application.jooq.tables.records.BookReadHistoryRecord;
import org.jooq.BatchBindStep;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.Query;
import org.jooq.RecordMapper;

import javax.annotation.Nonnull;
import java.util.List;

public class BookHistoryDao {

    private static final RecordMapper<BookReadHistoryRecord, BookReadActivityEntity> MAPPER = record ->
            BookReadActivityEntity.builder()
                    .withId(record.getId())
                    .withBookId(record.getBookId())
                    .withBeginPage(record.getBeginPage())
                    .withEndPage(record.getEndPage())
                    .withBeginTime(record.getBeginTime())
                    .withEndTime(record.getEndTime())
                    .build();

    private final DSLContext dslContext;

    public BookHistoryDao(@Nonnull DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public boolean store(@Nonnull BookReadActivityEntity entity) {
        int count = insertIntoStatement(entity).execute();
        return count == 1;
    }

    public boolean store(@Nonnull List<BookReadActivityEntity> entities) {
        BatchBindStep batchBindStep = dslContext.batch(insertIntoPreparedStatement());
        entities.forEach(entity -> batchBindStep.bind(
                entity.getBookId(),
                entity.getBeginPage(),
                entity.getEndPage(),
                entity.getBeginTime(),
                entity.getEndTime()
        ));
        int[] results = batchBindStep.execute();
        return results.length == entities.size();
    }

    @Nonnull
    public List<BookReadActivityEntity> fetchByBookId(int bookId) {
        return dslContext
                .selectFrom(Tables.BOOK_READ_HISTORY)
                .where(Tables.BOOK_READ_HISTORY.BOOK_ID.eq(bookId))
                .fetch(MAPPER);
    }

    private InsertSetMoreStep<BookReadHistoryRecord> insertIntoStatement(@Nonnull BookReadActivityEntity entity) {
        return dslContext.insertInto(Tables.BOOK_READ_HISTORY)
                .set(Tables.BOOK_READ_HISTORY.BOOK_ID, (int) entity.getBookId())
                .set(Tables.BOOK_READ_HISTORY.BEGIN_PAGE, entity.getBeginPage())
                .set(Tables.BOOK_READ_HISTORY.END_PAGE, entity.getEndPage())
                .set(Tables.BOOK_READ_HISTORY.BEGIN_TIME, entity.getBeginTime())
                .set(Tables.BOOK_READ_HISTORY.END_TIME, entity.getEndTime());
    }

    private Query insertIntoPreparedStatement() {
        return dslContext.insertInto(Tables.BOOK_READ_HISTORY,
                Tables.BOOK_READ_HISTORY.BOOK_ID,
                Tables.BOOK_READ_HISTORY.BEGIN_PAGE,
                Tables.BOOK_READ_HISTORY.END_PAGE,
                Tables.BOOK_READ_HISTORY.BEGIN_TIME,
                Tables.BOOK_READ_HISTORY.END_TIME
        ).values((Integer) null, null, null, null, null);
    }
}
