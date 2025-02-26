package com.reviewSystem.ApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity)
    {
//        authorize any exchange then authenticated then oauth by default login and then oauth resource  server type of jwt
      serverHttpSecurity.authorizeExchange(exchanges->exchanges.anyExchange()
              .authenticated())
              .oauth2Client(Customizer.withDefaults())
              .oauth2ResourceServer(oauth2->oauth2.jwt(Customizer.withDefaults()));
      return serverHttpSecurity.build();
    }
}
