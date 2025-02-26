package com.reviewSystem.userService.config;

import com.reviewSystem.userService.config.interceptor.RestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class configuration {

    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;
    //below commented code for simple rest template to call other microservice

//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate()
//    {
//        return new RestTemplate();
//    }

////below commented code for  rest template interceptor  to include security as well. to get token while calling other service
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {

        RestTemplate restTemplate=new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors=new ArrayList<>();
        interceptors.add(new RestTemplateInterceptor(manager(clientRegistrationRepository,auth2AuthorizedClientRepository)));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }


    // below is required for feign client interceptor only for security purpose.

    //declare bean of OAuth2AuthorizedClientManager
    @Bean
    public OAuth2AuthorizedClientManager manager(ClientRegistrationRepository clientRegistrationRepository,
    OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository)
    {
        OAuth2AuthorizedClientProvider provider= OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
        //build its object
//      ClientRegistrationRepository clientRegistrationRepository;--to store copy of clientRegistration
//        OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;
    DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager  =    new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,auth2AuthorizedClientRepository);
   defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
   return defaultOAuth2AuthorizedClientManager;
    }

}
