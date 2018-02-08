package com.oneuse.dainbow.accounter.dainbowaccounter.config;

import com.oneuse.dainbow.accounter.dainbowaccounter.config.logging.GlobalRequestHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private GlobalRequestHandlerInterceptor globalRequestHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalRequestHandlerInterceptor).addPathPatterns("/**");
    }
}
