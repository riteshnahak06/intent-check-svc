package com.bank.intent_check_svc.service;

import com.bank.intent_check_svc.dto.ChatRequest;
import com.bank.intent_check_svc.dto.ChatResponse;
import com.bank.intent_check_svc.intent.IntentHandler;
import com.bank.intent_check_svc.utils.IntentHandlerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatOrchestrationService {

    private final ReactiveChatIntentService intentService;
    private final IntentHandlerFactory handlerFactory;

    public Mono<ChatResponse> handle(ChatRequest request) {

        return intentService.analyze(request.getMessage())
                .doOnSubscribe(sub ->
                        log.info(
                                "[CHAT][REQ] sessionId={} customerId={} message=\"{}\"",
                                request.getSessionId(),
                                request.getCustomerId(),
                                request.getMessage())
                )
                .doOnNext(intentResult ->
                        log.info(
                                "[CHAT][INTENT] sessionId={} intent={}",
                                request.getSessionId(),
                                intentResult.getIntent()
                        )
                )
                .flatMap(intentResult -> {
                    IntentHandler handler =
                            handlerFactory.getHandler(intentResult.getIntent());

                    log.info(
                            "[CHAT][HANDLER] sessionId={} handler={}",
                            request.getSessionId(),
                            handler.getClass().getSimpleName()
                    );

                    return handler.handle(request, intentResult);
                })
                .doOnNext(response ->
                        log.info(
                                "[CHAT][RESP] sessionId={} expectReply={} message=\"{}\"",
                                request.getSessionId(),
                                response.isExpectUserReply(),
                                response.getMessage()
                        )
                )
                .doOnError(ex ->
                        log.error(
                                "[CHAT][ERROR] sessionId={} error={}",
                                request.getSessionId(),
                                ex.getMessage(),
                                ex
                        )
                );
    }

}
