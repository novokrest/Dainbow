package com.oneuse.dainbow.books.application.repository;

import com.oneuse.dainbow.books.application.entity.BookReadActivityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


@RestResource(path = "history", rel = "history")
public interface BookReadHistoryRepository extends PagingAndSortingRepository<BookReadActivityEntity, Long> {

//    @RestResource(path = "book", rel = "book")
    List<BookReadActivityEntity> findByBookId(@Param("bookId") Long bookId);
}

