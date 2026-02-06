package com.bank.intent_check_svc.dto;

import lombok.Data;

@Data
public class ChatRequest {

    // Raw user message from chat UI
    private String message;

    // Logged-in customer identifier (from JWT / gateway)
    private String customerId;

    // Conversation/session id (stored in Redis)
    private String sessionId;

    // Channel: MOBILE, WEB, WHATSAPP, etc.
    private String channel;

    // Optional metadata (future use)
    private Metadata metadata;

    @Data
    public static class Metadata {
        private String language;     // en, hi, ph
        private String appVersion;
        private String deviceId;
    }
}
