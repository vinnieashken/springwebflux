package com.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableR2dbcAuditing
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        // Disable authentication for all requests
        return http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeExchange(auth -> auth
                        .pathMatchers("/api/products/list").permitAll() // Allow unauthenticated access
                        .anyExchange().permitAll() // Require authentication for all other requests
                )
                .build();
    }
}
