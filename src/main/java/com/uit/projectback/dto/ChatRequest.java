package com.uit.projectback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Lombok: auto-generates getters, setters, toString, etc.
@NoArgsConstructor  // Creates empty constructor
@AllArgsConstructor  // Creates constructor with all fields
public class ChatRequest {
    private String question;
}
