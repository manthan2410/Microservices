package com.reviewSystem.ratingService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
// authorize  then authenticate then resourceserver then jwt configure.
        httpSecurity.authorizeHttpRequests((requests)->requests.anyRequest()
                .authenticated()).oauth2ResourceServer(oauth2->oauth2.jwt(Customizer.withDefaults()));
        return httpSecurity.build();
    }
}
