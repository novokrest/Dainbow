package com.oneuse.dainbow.books.dao;

import com.oneuse.dainbow.books.dao.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "books", rel = "books")
public interface BookDao extends CrudRepository<BookEntity, Long> {
}
