package com.oneuse.dainbow.books.application.dao;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

import com.oneuse.dainbow.books.application.entity.BookReadActivityEntity;
import com.oneuse.dainbow.books.application.jooq.Tables;
import com.oneuse.dainbow.books.application.jooq.tables.records.BookRecord;
import org.jooq.DSLContext;

import com.oneuse.dainbow.books.application.entity.BookEntity;
import org.jooq.RecordMapper;

public class BookDao {

    private static final RecordMapper<BookRecord, BookEntity> MAPPER = record ->
            BookEntity.builder()
                    .withId(record.getId())
                    .withTitle(record.getTitle())
                    .withAuthor(record.getAuthor())
                    .withTotalPagesCount(record.getPagesCount())
                    .build();

    private final DSLContext dslContext;

    public BookDao(@Nonnull DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public int store(@Nonnull BookEntity entity) {
        return dslContext.insertInto(Tables.BOOK)
                .set(Tables.BOOK.ID, entity.getId())
                .set(Tables.BOOK.TITLE, entity.getTitle())
                .set(Tables.BOOK.AUTHOR, entity.getAuthor())
                .set(Tables.BOOK.PAGES_COUNT, entity.getTotalPagesCount())
                .returning(Tables.BOOK.ID)
                .fetchOne()
                .getId();
    }

    @Nonnull
    public Optional<BookEntity> fetchById(int bookId) {
        return dslContext
                .selectFrom(Tables.BOOK)
                .where(Tables.BOOK.ID.eq(bookId))
                .fetchOptional(MAPPER);
    }
}

