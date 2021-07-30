package com.miw.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .mvcMatcher("/api/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").hasAuthority("SCOPE_shop:read")
                .antMatchers(HttpMethod.POST, "/api/**").hasAuthority("SCOPE_shop:write")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}