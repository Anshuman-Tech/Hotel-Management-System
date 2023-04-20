package com.anshuman.userservice.configuration;

import com.anshuman.userservice.configuration.Interceptor.RestTemplateInterceptor;
import com.netflix.discovery.converters.Auto;
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
public class MyConfig {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;


    /*
    The UserService is created as an OAuth client as it is calling other services, but Hotel and Rating services are not calling
    any other service as per the system architecture, so they will not be created as a client.
     */

    @Bean
    @LoadBalanced //For using the clients by name.
    public RestTemplate restTemplate(){

//        return new RestTemplate();
        RestTemplate restTemplate = new RestTemplate();

        //Type of the interceptors must be same as what is being passed by the setInterceptors(). We can check it my Ctrl + click on the method.
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new RestTemplateInterceptor(manager(clientRegistrationRepository,auth2AuthorizedClientRepository)));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    /*
    The RestTemplate interceptor is used to take any HttpRequest from a RestTemplate object and add the token in the headers and then forward
    the request to the called client or service.
     */

    /*
    Note - If the bean is declared in the same class in which it needs to be used or autowired, then @Autowired Annotation cannot be used.
    The object needs to be created explicitly.
     */

    //Declaring the bean of OAuth2AuthorizedClientManager
    @Bean
    public OAuth2AuthorizedClientManager manager(
            ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository
    ){
        OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
        DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientRepository);
        defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
        return defaultOAuth2AuthorizedClientManager;
    }
}
