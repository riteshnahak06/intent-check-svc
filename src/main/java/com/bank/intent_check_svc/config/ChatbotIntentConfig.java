package com.bank.intent_check_svc.config;

import com.bank.intent_check_svc.utils.ChatIntent;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "chatbot")
public class ChatbotIntentConfig {

    private Map<ChatIntent, IntentConfig> intents = new EnumMap<>(ChatIntent.class);

    @Data
    public static class IntentConfig {
        private List<String> keywords = List.of();
    }
}
