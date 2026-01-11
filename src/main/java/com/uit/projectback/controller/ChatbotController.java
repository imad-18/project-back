package com.uit.projectback.controller;

import com.uit.projectback.dto.ChatResponse;
import com.uit.projectback.model.ChatMessage;
import com.uit.projectback.service.ChatbotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uit.projectback.dto.ChatRequest;

@RestController  // Combines @Controller + @ResponseBody (returns JSON automatically)
@RequestMapping("/chat")  // Base URL path
@RequiredArgsConstructor  // Inject ChatbotService
public class ChatbotController {

    private final ChatbotService chatbotService;

    @PostMapping("/ask")  // Full URL: POST /chat/ask
    public ResponseEntity<ChatResponse> askQuestion(@RequestBody ChatRequest request) {
        // 1. Validate input
        if (request.getQuestion() == null || request.getQuestion().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ChatResponse(null, "Question cannot be empty"));
        }

        // 2. Delegate to service (controller does NOT do business logic)
        ChatMessage message = chatbotService.askQuestion(request);

        // 3. Check for errors
        if (message.isHasError()) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(new ChatResponse(null, message.getAnswer()));
        }

        // 4. Return success response
        return ResponseEntity.ok(new ChatResponse(message.getAnswer(), "Question cannot be empty"));
    }
}