package com.oneuse.dainbow.books.application.repository;

import com.oneuse.dainbow.books.application.entity.BookEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "/books", collectionResourceRel = "books")
public interface BookRepository extends PagingAndSortingRepository<BookEntity, Long> {
}