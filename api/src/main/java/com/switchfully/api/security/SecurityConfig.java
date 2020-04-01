package com.switchfully.api.security;

//import com.switchfully.api.endpoints.BookController; //TODO: change to correct endpoints
//import com.switchfully.api.endpoints.BorrowController;

import com.switchfully.api.endpoints.UserController;
import com.switchfully.api.exceptions.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationEntryPoint authEntryPoint;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    public SecurityConfig(AuthenticationEntryPoint authEntryPoint, UserAuthenticationProvider userAuthenticationProvider) {
        this.authEntryPoint = authEntryPoint;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.csrf().disable().authorizeRequests()
                .antMatchers(UserController.USER_RESOURCE_PATH + "**").permitAll()
//                .antMatchers(BookController.BOOK_RESOURCE_PATH + "/**").permitAll() //TODO: manage access
//                .antMatchers(BorrowController.BORROW_RESOURCE_PATH + "/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .authenticationEntryPoint(authEntryPoint)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userAuthenticationProvider);
    }

}
