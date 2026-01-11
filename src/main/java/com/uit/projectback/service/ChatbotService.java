package com.uit.projectback.service;

import com.uit.projectback.dto.ChatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import com.uit.projectback.model.ChatMessage;
import com.uit.projectback.config.ChatbotConfig;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service  // Tells Spring: "This is a service, manage it as a bean"
@RequiredArgsConstructor  // Lombok: creates constructor for 'final' fields
public class ChatbotService {

    private final ChatbotConfig chatbotConfig;  // Injected by Spring
    private final RestTemplate restTemplate;     // Injected by Spring

    public ChatMessage askQuestion(ChatRequest request) {
        // 1. Prepare the HTTP request (form data)
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("question", request.getQuestion());

        // 2. Set headers (FastAPI expects form data)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity =
                new HttpEntity<>(formData, headers);

        // 3. Make the POST request to FastAPI
        ResponseEntity<String> response = restTemplate.exchange(
                chatbotConfig.getApiUrl(),  // "http://localhost:8000/api/chat"
                HttpMethod.POST,
                requestEntity,
                String.class  // Expect String response
        );

        // 4. Convert to ChatMessage and return
        return new ChatMessage(request.getQuestion(), response.getBody());
    }

}