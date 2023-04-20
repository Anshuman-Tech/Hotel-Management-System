package com.anshuman.apigateway.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class MySecurityConfiguration{

    /*
    @EnableWebFluxSecurity is used since we are use WebFlux as we have implemented Spring cloud gateway dependency instead of SpringWeb otherwise @EnableWebSecurity would be used.
    SecurityWebFilterChain is used because of WebFlux otherwise SecurityFilterChain would be used.
    In the parameter ServerHttpSecurity is used instead of HttpSecurity as we are using WebFlux
     */

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .disable()
                .csrf()
                .disable()
                .authorizeExchange() //for HttpSecurity - authorizeRequest()
                .anyExchange() //for HttpSecurity - anyRequest()
                .authenticated()
                .and()
                .oauth2Client()
                .and()
                .oauth2ResourceServer()
                .jwt();
        return httpSecurity.build();
    }
}
