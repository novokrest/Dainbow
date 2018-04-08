package com.oneuse.dainbow.accounter.dainbowaccounter.config.security;

import com.oneuse.dainbow.accounter.dainbowaccounter.process.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

//@Import(SimpleOauth2Configuration.class)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private Filter ssoFilter;

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
                .antMatchers(Endpoint.LOGIN.getPath(), Endpoint.UNAUTHORIZED.getPath(), Endpoint.OAUTH_TOKEN.getPath()).permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .failureUrl("/login?error=true")
                .permitAll().and()
                .logout()
                        .logoutSuccessUrl("/login")
                        .permitAll().and()
//                .exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(Endpoint.LOGIN.getPath()))

//                .authorizeRequests()
//                    .antMatchers("/", "/login", "/webjars/**", "/test")
//                    .permitAll()
//                .anyRequest()
//                    .authenticated()
//                .and()
//                    .formLogin().loginPage("/login").permitAll()
//                .and()
//                    .logout().logoutSuccessUrl("/").permitAll()
//                .and()
//                    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
//                    .exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
//                .and()
//                    .addFilterBefore(ssoFilter, BasicAuthenticationFilter.class)
                ;
    }
}
