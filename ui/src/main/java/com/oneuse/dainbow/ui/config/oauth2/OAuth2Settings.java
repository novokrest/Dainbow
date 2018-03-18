package com.oneuse.dainbow.ui.config.oauth2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "oauth2")
public class OAuth2Settings {

    @NestedConfigurationProperty
    private ClientProperties client = new ClientProperties();

    @NestedConfigurationProperty
    private AuthorizationServerProperties authorizationServer = new AuthorizationServerProperties();

    public AuthorizationServerProperties getAuthorizationServer() {
        return authorizationServer;
    }

    public ClientProperties getClient() {
        return client;
    }

    public static class ClientProperties {

        private String id;

        private String secret;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }

    public static class AuthorizationServerProperties {

        private String url;

        private String checkTokenPath;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCheckTokenPath() {
            return checkTokenPath;
        }

        public void setCheckTokenPath(String checkTokenPath) {
            this.checkTokenPath = checkTokenPath;
        }
    }
}
