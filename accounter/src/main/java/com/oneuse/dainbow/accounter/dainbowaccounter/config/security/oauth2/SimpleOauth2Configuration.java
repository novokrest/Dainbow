package com.oneuse.dainbow.accounter.dainbowaccounter.config.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@EnableOAuth2Client
//@EnableAuthorizationServer
//@Configuration
public class SimpleOauth2Configuration {

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }


    @Bean
    public Filter ssoFilter(List<Oauth2ProviderSettings> settings) {
        CompositeFilter filter = new CompositeFilter();
        filter.setFilters(buildFilters(settings));
        return filter;
    }

    private List<Filter> buildFilters(List<Oauth2ProviderSettings> settingsList) {
        return settingsList.stream().map(settings -> {
            OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(settings.getLoginUrl());
            OAuth2RestTemplate oauth2RestTemplate = new OAuth2RestTemplate(settings.getClient(), oauth2ClientContext);
            filter.setRestTemplate(oauth2RestTemplate);

            UserInfoTokenServices tokenServices = new UserInfoTokenServices(settings.getResource().getUserInfoUri(), settings.getClient().getClientId());
            tokenServices.setRestTemplate(oauth2RestTemplate);
            filter.setTokenServices(tokenServices);

            return filter;
        }).collect(Collectors.toList());
    }

    @Bean
    @ConfigurationProperties("oauth2.google")
    public Oauth2ProviderSettings google() {
        return new Oauth2ProviderSettings();
    }
}
