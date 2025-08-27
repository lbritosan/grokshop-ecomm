package com.lbritosan.grokshop.config;

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
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Libera H2 console
                        .anyRequest().authenticated() // Outros endpoints requerem autenticação
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**") // Desativa CSRF para H2
                )
                .headers(headers -> headers
                        .frameOptions().sameOrigin() // Permite iframes do H2 console
                );
        return http.build();
    }
}