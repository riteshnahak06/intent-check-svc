package com.bank.intent_check_svc.intent;

import com.bank.intent_check_svc.dto.ChatRequest;
import com.bank.intent_check_svc.dto.ChatResponse;
import com.bank.intent_check_svc.dto.IntentResult;
import com.bank.intent_check_svc.utils.ChatIntent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UnknownIntentHandler implements IntentHandler {

    @Override
    public ChatIntent intent() {
        return ChatIntent.UNKNOWN;
    }

    @Override
    public Mono<ChatResponse> handle(ChatRequest request, IntentResult result) {
        return Mono.just(ChatResponse.of(
                "Sorry, I didn’t understand that. You can ask about balance, transactions or loans."));
    }
}
