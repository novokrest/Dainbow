package com.oneuse.dainbow.books.config;

import com.oneuse.dainbow.books.repositories.entity.BookEntity;
import com.oneuse.dainbow.books.repositories.entity.BookReadProgressEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration configuration) {
        configuration.exposeIdsFor(BookEntity.class, BookReadProgressEntity.class);
    }
}
