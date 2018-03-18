package com.oneuse.dainbow.accounter.dainbowaccounter.config.error;

import com.oneuse.dainbow.accounter.dainbowaccounter.process.Endpoint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ServletCustomizer {

    @Bean
    public EmbeddedServletContainerCustomizer customizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, Endpoint.UNAUTHORIZED.getPath()));
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, Endpoint.UNAUTHORIZED.getPath()));
        };
    }
}
