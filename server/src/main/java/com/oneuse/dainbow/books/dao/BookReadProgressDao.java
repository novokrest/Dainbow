package com.oneuse.dainbow.books.dao;

import com.oneuse.dainbow.books.dao.entity.BookReadProgressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(exported = false)
public interface BookReadProgressDao extends CrudRepository<BookReadProgressEntity, Long> {
}
