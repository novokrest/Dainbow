package com.oneuse.dainbow.config;

import com.oneuse.dainbow.RootPackageMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackageClasses = RootPackageMarker.class,
               excludeFilters = {
                       @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
                       @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.oneuse\\.dainbow\\.web\\..*"),
                       @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
        })
public class TestConfig {
}
