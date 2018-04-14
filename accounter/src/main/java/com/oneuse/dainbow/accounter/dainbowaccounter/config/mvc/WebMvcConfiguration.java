package com.oneuse.dainbow.accounter.dainbowaccounter.config.mvc;

import com.oneuse.dainbow.accounter.dainbowaccounter.process.endpoints.PublicEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(PublicEndpoint.LOGIN.getPath()).setViewName("login.html");
        registry.addViewController(PublicEndpoint.UNAUTHORIZED.getPath()).setViewName("unauthorized.html");
        registry.addViewController(PublicEndpoint.HOME.getPath()).setViewName("home.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
