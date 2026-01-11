package com.uit.projectback.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ChatMessage {
    private String question;
    private String answer;
    private LocalDateTime timestamp;
    private boolean hasError;

    // Custom constructor for convenience
    public ChatMessage(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.timestamp = LocalDateTime.now();
        this.hasError = false;
    }
}