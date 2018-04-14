package com.oneuse.dainbow.accounter.dainbowaccounter.config.security.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.List;
import java.util.stream.Collectors;

@EnableOAuth2Client
@Configuration
public class OAuth2ClientConfiguration {

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
    public Filter ssoFilter(List<OAuth2ProviderSettings> settings) {
        CompositeFilter filter = new CompositeFilter();
        filter.setFilters(buildFilters(settings));
        return filter;
    }

    private List<Filter> buildFilters(List<OAuth2ProviderSettings> settingsList) {
        return settingsList.stream().map(this::buildFilter).collect(Collectors.toList());
    }

    private Filter buildFilter(OAuth2ProviderSettings settings) {
        OAuth2RestTemplate oauth2RestTemplate = new OAuth2RestTemplate(settings.getClient(), oauth2ClientContext);

        UserInfoTokenServices tokenServices = new UserInfoTokenServices(settings.getResource().getUserInfoUri(), settings.getClient().getClientId());
        tokenServices.setRestTemplate(oauth2RestTemplate);

        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(settings.getLoginUrl());
        filter.setRestTemplate(oauth2RestTemplate);
        filter.setTokenServices(tokenServices);

        return filter;
    }

    @Bean
    @ConfigurationProperties("oauth2.google")
    public OAuth2ProviderSettings google() {
        return new OAuth2ProviderSettings();
    }
}
