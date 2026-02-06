package com.bank.intent_check_svc.service;

import com.bank.intent_check_svc.dto.ChatRequest;
import com.bank.intent_check_svc.dto.ChatResponse;
import com.bank.intent_check_svc.intent.IntentHandler;
import com.bank.intent_check_svc.utils.IntentHandlerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ChatOrchestrationService {

    private final ReactiveChatIntentService intentService;
    private final IntentHandlerFactory handlerFactory;

    public Mono<ChatResponse> handle(ChatRequest request) {

        return intentService.analyze(request.getMessage())
                .flatMap(intentResult -> {

                    IntentHandler handler =
                            handlerFactory.getHandler(intentResult.getIntent());

                    return handler.handle(request, intentResult);
                });
    }
}
