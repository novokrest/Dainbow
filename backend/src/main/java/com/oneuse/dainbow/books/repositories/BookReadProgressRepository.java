package com.oneuse.dainbow.books.repositories;

import com.oneuse.dainbow.books.repositories.entity.BookReadProgressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface BookReadProgressRepository extends CrudRepository<BookReadProgressEntity, Long> {
}
