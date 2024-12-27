package com.example.test_analyse_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Désactive la protection CSRF
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/test-analyses/**").permitAll()  // Permits all access
                        .anyRequest().authenticated()  // Toutes les autres routes nécessitent une authentification
                );
        return http.build();
    }
}
