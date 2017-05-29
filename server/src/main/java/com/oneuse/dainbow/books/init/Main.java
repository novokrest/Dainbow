package com.oneuse.dainbow.books.init;

import com.oneuse.dainbow.books.config.PersistenceConfig;
import com.oneuse.dainbow.books.config.RootConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.default", "dev");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class,
                                                                                       PersistenceConfig.class);
        BookDatabaseInitializer databaseInitializer = applicationContext.getBean(BookDatabaseInitializer.class);
        databaseInitializer.initialize();
    }
}