package com.anshuman.userservice.configutation;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    @LoadBalanced //For using the clients by name.
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
