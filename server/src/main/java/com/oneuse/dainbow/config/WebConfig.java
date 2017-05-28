package com.oneuse.dainbow.config;

import com.oneuse.dainbow.web.WebPackageMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.servlet.ServletContext;
import java.util.Properties;


@Configuration
@ComponentScan(basePackageClasses = WebPackageMarker.class)
public class WebConfig extends WebMvcConfigurerAdapter implements ServletContextAware {
    private ServletContext servletContext;

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
        exceptionMappings.put("com.oneuse.dainbow.exceptions.BookNotFoundException", "errors/404");
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
}
