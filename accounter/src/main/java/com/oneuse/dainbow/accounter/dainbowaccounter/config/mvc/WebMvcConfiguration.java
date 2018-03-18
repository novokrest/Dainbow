package com.oneuse.dainbow.accounter.dainbowaccounter.config.mvc;

import com.oneuse.dainbow.accounter.dainbowaccounter.process.Endpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(Endpoint.LOGIN.getPath()).setViewName("login.html");
        registry.addViewController(Endpoint.UNAUTHORIZED.getPath()).setViewName("unauthorized.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
