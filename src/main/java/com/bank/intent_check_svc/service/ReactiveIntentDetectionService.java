package com.bank.intent_check_svc.service;

import com.bank.intent_check_svc.config.ChatbotIntentConfig;
import com.bank.intent_check_svc.utils.ChatIntent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReactiveIntentDetectionService {

    private final ChatbotIntentConfig config;

    public Mono<ChatIntent> detect(String message) {

        return Mono.fromSupplier(() -> {

            if (message == null || message.isBlank()) {
                log.info("[CHAT][INTENT][EMPTY] message is null or blank → UNKNOWN");
                return ChatIntent.UNKNOWN;
            }

            String msg = message.toLowerCase();
            log.debug("[CHAT][INTENT][CHECK] normalizedMessage=\"{}\"", msg);

            for (Map.Entry<ChatIntent, ChatbotIntentConfig.IntentConfig> entry
                    : config.getIntents().entrySet()) {

                ChatIntent intent = entry.getKey();

                for (String keyword : entry.getValue().getKeywords()) {
                    if (msg.contains(keyword.toLowerCase())) {

                        log.info(
                                "[CHAT][INTENT][MATCH] intent={} keyword=\"{}\"",
                                intent,
                                keyword
                        );

                        return intent;
                    }
                }
            }

            log.info("[CHAT][INTENT][NO_MATCH] intent=UNKNOWN");
            return ChatIntent.UNKNOWN;
        });
    }
}
