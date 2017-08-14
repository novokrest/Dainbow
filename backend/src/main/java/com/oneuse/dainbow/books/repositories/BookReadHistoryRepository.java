package com.oneuse.dainbow.books.repositories;

import com.oneuse.dainbow.books.repositories.entity.BookReadActivityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "history", rel = "history")
public interface BookReadHistoryRepository extends CrudRepository<BookReadActivityEntity, Long> {
}