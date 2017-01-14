package com.oneuse.dainbow.init;

import com.oneuse.dainbow.config.RootConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        BookDatabaseInitializer databaseInitializer = applicationContext.getBean(BookDatabaseInitializer.class);
        databaseInitializer.initialize();
    }
}