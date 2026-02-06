package com.bank.intent_check_svc.dto;

import com.bank.intent_check_svc.utils.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponse {

    // Text shown to user
    private String message;

    // Response type for UI rendering
    private ResponseType type;

    // Whether conversation continues
    private boolean expectUserReply;

    // Optional structured data
    private Payload payload;

    public static ChatResponse of(String message) {
        return new ChatResponse(message, ResponseType.TEXT, false, null);
    }

    public static ChatResponse ask(String message) {
        return new ChatResponse(message, ResponseType.TEXT, true, null);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Payload {
        private Object data;
    }
}
