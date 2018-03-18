package com.oneuse.dainbow.ui.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

//@Import(OAuth2Settings.class)
@EnableResourceServer
@Configuration
public class Oauth2ResourceServerConfiguration {

    @Autowired
    private OAuth2Settings oauth2Settings;

    @Primary
    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(getCheckTokenUrl(oauth2Settings));
        tokenService.setClientId(oauth2Settings.getClient().getId());
        tokenService.setClientSecret(oauth2Settings.getClient().getSecret());
        return tokenService;
    }

    private static String getCheckTokenUrl(OAuth2Settings oauth2Settings) {
        return oauth2Settings.getAuthorizationServer().getUrl() +
                oauth2Settings.getAuthorizationServer().getCheckTokenPath();
    }

    @Bean
    public TokenStore tokenStore(DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }
}
