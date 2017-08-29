package com.oneuse.dainbow.books.repositories;

import com.oneuse.dainbow.books.repositories.entity.BookReadActivityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "http://localhost:8080", methods = RequestMethod.GET)
@RestResource(path = "history", rel = "history")
public interface BookReadHistoryRepository extends CrudRepository<BookReadActivityEntity, Long> {
}
