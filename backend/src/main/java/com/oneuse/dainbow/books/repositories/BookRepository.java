package com.oneuse.dainbow.books.repositories;

import com.oneuse.dainbow.books.repositories.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "books", rel = "books")
public interface BookRepository extends CrudRepository<BookEntity, Long> {
}
