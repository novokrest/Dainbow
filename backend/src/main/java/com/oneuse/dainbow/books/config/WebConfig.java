package com.oneuse.dainbow.books.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.ServletContext;
import java.util.Properties;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements ServletContextAware {

    private ServletContext servletContext;

    @Value("${spring.data.rest.base-path}")
    private String dataRestBasePath;

    @Value("${frontend.server.url}")
    private String frontendServerUrl;

    @Bean
    public ServletContext servletContext() {
        return servletContext;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public SimpleMappingExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        Properties exceptionMappings = new Properties();
        exceptionMappings.put("com.oneuse.dainbow.books.exceptions.BookNotFoundException", "errors/404");
        exceptionMappings.put("java.lang.Exception", "error");
        exceptionMappings.put("java.lang.RuntimeException", "error");

        exceptionResolver.setExceptionMappings(exceptionMappings);

        Properties statusCodes = new Properties();
        statusCodes.put("errors/404", "404");
        statusCodes.put("error", "500");

        exceptionResolver.setStatusCodes(statusCodes);

        return exceptionResolver;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true).maxAge(3600);
        ;
    }
}
