package com.uit.projectback.config;

import com.uit.projectback.config.ChatbotConfig;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, ChatbotConfig config) {
        return builder
                .setConnectTimeout(Duration.ofMillis(config.getTimeout()))
                .setReadTimeout(Duration.ofMillis(config.getTimeout()))
                .build();
    }
}
