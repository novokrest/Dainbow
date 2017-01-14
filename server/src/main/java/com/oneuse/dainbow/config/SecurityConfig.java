//package com.oneuse.dainbow.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    DataSource dataSource;
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeRequests()
//                    .antMatchers("/books/admin").authenticated()
//                    .anyRequest().permitAll()
//                    .and()
//                .requiresChannel()
//                    .antMatchers(HttpMethod.POST, "books/admin/edit").requiresSecure()
//                    .antMatchers("/books").requiresInsecure()
//                    .and()
//                .formLogin()
//                    .loginPage("books/login")
//                    .and()
//                .logout()
//                    .logoutSuccessUrl("/")
//                    .and()
//                .httpBasic()
//                    .and()
//                .rememberMe()
//                    .tokenValiditySeconds(3600)
//                    .key("BooksKey");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//            .dataSource(dataSource)
//            .passwordEncoder(new StandardPasswordEncoder("dbw13"));
//    }
//}
