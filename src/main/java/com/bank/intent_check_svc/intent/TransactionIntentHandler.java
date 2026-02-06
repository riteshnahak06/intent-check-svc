package com.bank.intent_check_svc.intent;

import com.bank.intent_check_svc.dto.ChatRequest;
import com.bank.intent_check_svc.dto.ChatResponse;
import com.bank.intent_check_svc.dto.IntentResult;
import com.bank.intent_check_svc.utils.ChatIntent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TransactionIntentHandler implements IntentHandler {

    @Override
    public ChatIntent intent() {
        return ChatIntent.LAST_TRANSACTIONS;
    }

    @Override
    public Mono<ChatResponse> handle(ChatRequest request, IntentResult result) {

        int count = result.getNumber() != null ? result.getNumber() : 5;

        return Mono.just(ChatResponse.of(
                "Fetching last " + count + " transactions..."));
    }
}
