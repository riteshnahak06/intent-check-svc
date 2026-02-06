package com.bank.intent_check_svc.intent;

import com.bank.intent_check_svc.dto.ChatRequest;
import com.bank.intent_check_svc.dto.ChatResponse;
import com.bank.intent_check_svc.dto.IntentResult;
import com.bank.intent_check_svc.utils.ChatIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BalanceIntentHandler implements IntentHandler {

    @Override
    public ChatIntent intent() {
        return ChatIntent.CHECK_BALANCE;
    }

    @Override
    public Mono<ChatResponse> handle(ChatRequest request, IntentResult result) {
        return Mono.just(ChatResponse.of(
                "Fetching your account balance..."));
    }
}
