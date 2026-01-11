package com.uit.projectback.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data  // This generates getters and setters automatically!
@Configuration
@ConfigurationProperties(prefix = "chatbot")
public class ChatbotConfig {
    private String apiUrl;
    private int timeout = 30000;
}