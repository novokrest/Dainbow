package com.oneuse.dainbow.accounter.dainbowaccounter.config.security;

import com.oneuse.dainbow.accounter.dainbowaccounter.process.endpoints.ApiEndpoint;
import com.oneuse.dainbow.accounter.dainbowaccounter.process.endpoints.PublicEndpoint;
import com.oneuse.dainbow.accounter.dainbowaccounter.process.endpoints.ResourceEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

import static com.oneuse.dainbow.accounter.dainbowaccounter.process.endpoints.PublicEndpoint.toErrorPath;

@EnableWebSecurity
@Configuration
public class SelfSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountSchemaSettings accountSchemaSettings;

    @Autowired
    private DataSource dataSource;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(accountSchemaSettings.getUsersByUsername())
                .authoritiesByUsernameQuery(accountSchemaSettings.getAuthoritiesByUsername())
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                        .antMatchers(
                                PublicEndpoint.LOGIN.getPath(),
                                PublicEndpoint.UNAUTHORIZED.getPath(),
                                ApiEndpoint.SIGN_IN.getPath(),
                                ResourceEndpoint.WEBJARS.getPath(),
                                ResourceEndpoint.CSS.getPath())
                        .permitAll()
                        .anyRequest().authenticated()
                        .and()

                .formLogin()
                        .loginPage(PublicEndpoint.LOGIN.getPath())
                        .failureUrl(toErrorPath(PublicEndpoint.LOGIN))
                        .loginProcessingUrl(ApiEndpoint.SIGN_IN.getPath())
                        .defaultSuccessUrl(PublicEndpoint.HOME.getPath())
                        .permitAll()
                        .and()

                .logout()
                        .logoutSuccessUrl(PublicEndpoint.LOGIN.getPath())
                        .permitAll()
                        .and()

                .exceptionHandling()
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(PublicEndpoint.LOGIN.getPath()))
                        .and()

                .csrf()
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

                ;
    }
}
