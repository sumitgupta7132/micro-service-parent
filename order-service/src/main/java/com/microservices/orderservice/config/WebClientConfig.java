package com.microservices.orderservice.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Data
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
