package com.bank.intent_check_svc.service;

import com.bank.intent_check_svc.dto.IntentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveChatIntentService {

    private final ReactiveIntentDetectionService intentDetectionService;

    public Mono<IntentResult> analyze(String message) {

        return intentDetectionService.detect(message)
                .map(intent -> {
                    IntentResult result = new IntentResult();
                    result.setIntent(intent);
                    return result;
                });
    }
}
