package com.oneuse.dainbow.books.application.config;

import com.oneuse.dainbow.books.application.entity.BookEntity;
import com.oneuse.dainbow.books.application.entity.BookReadActivityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import java.util.concurrent.TimeUnit;


@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

    @Autowired
    CorsRegistryConfigurer corsRegistryConfigurer;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration configuration) {
        configuration.exposeIdsFor(BookEntity.class, BookReadActivityEntity.class);
        corsRegistryConfigurer.configureCors(configuration.getCorsRegistry());
    }
}
