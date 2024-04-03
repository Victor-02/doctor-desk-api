package com.tcc.tccbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception {
        httpSec
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/swagger-resources/**",
                        "/swagger-ui.html/**",
                        "/webjars/**",
                        "favicon.ico",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/api/user/login",
                        "/api/user/create",
                        "/swagger-ui/**")
                .permitAll()
                .anyRequest().authenticated().and().cors();
        httpSec.addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSec.build();
    }
}