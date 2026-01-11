package com.uit.projectback.dto;

public class ChatResponse {
    private String answer;
    private String error;

    public ChatResponse(String answer, String questionCannotBeEmpty) {
        this.answer = answer;
    }
}