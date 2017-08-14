package com.oneuse.dainbow.books.dao;

import com.oneuse.dainbow.books.dao.entity.BookReadActivityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "activity", rel = "activity")
public interface BookReadHistoryDao extends CrudRepository<BookReadActivityEntity, Long> {
}
