package com.bank.intent_check_svc.service;

import com.bank.intent_check_svc.config.ChatbotIntentConfig;
import com.bank.intent_check_svc.utils.ChatIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReactiveIntentDetectionService {

    private final ChatbotIntentConfig config;

    public Mono<ChatIntent> detect(String message) {

        return Mono.fromSupplier(() -> {

            if (message == null || message.isBlank()) {
                return ChatIntent.UNKNOWN;
            }

            String msg = message.toLowerCase();

            for (Map.Entry<ChatIntent, ChatbotIntentConfig.IntentConfig> entry
                    : config.getIntents().entrySet()) {
                for (String keyword : entry.getValue().getKeywords()) {
                    if (msg.contains(keyword.toLowerCase())) {
                        return entry.getKey();
                    }
                }
            }

            return ChatIntent.UNKNOWN;
        });
    }
}
