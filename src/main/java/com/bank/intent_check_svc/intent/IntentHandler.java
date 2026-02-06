package com.bank.intent_check_svc.intent;

import com.bank.intent_check_svc.dto.ChatRequest;
import com.bank.intent_check_svc.dto.ChatResponse;
import com.bank.intent_check_svc.dto.IntentResult;
import com.bank.intent_check_svc.utils.ChatIntent;
import reactor.core.publisher.Mono;

public interface IntentHandler {
    ChatIntent intent();
    Mono<ChatResponse> handle(ChatRequest request, IntentResult result);
}
