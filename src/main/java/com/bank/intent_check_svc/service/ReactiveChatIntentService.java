package com.bank.intent_check_svc.service;

import com.bank.intent_check_svc.dto.IntentResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReactiveChatIntentService {

    private final ReactiveIntentDetectionService intentDetectionService;

    public Mono<IntentResult> analyze(String message) {

        return intentDetectionService.detect(message)
                .doOnSubscribe(sub ->
                        log.info(
                                "[CHAT][INTENT][START] message=\"{}\"",
                                message
                        )
                )
                .map(intent -> {
                    IntentResult result = new IntentResult();
                    result.setIntent(intent);
                    return result;
                })
                .doOnNext(result ->
                        log.info(
                                "[CHAT][INTENT][DETECTED] intent={}",
                                result.getIntent()
                        )
                )
                .doOnError(ex ->
                        log.error(
                                "[CHAT][INTENT][ERROR] error={}",
                                ex.getMessage(),
                                ex
                        )
                );
    }

}
