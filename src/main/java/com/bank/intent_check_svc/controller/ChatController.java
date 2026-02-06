package com.bank.intent_check_svc.controller;

import com.bank.intent_check_svc.dto.ChatRequest;
import com.bank.intent_check_svc.dto.ChatResponse;
import com.bank.intent_check_svc.service.ChatOrchestrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatOrchestrationService chatService;

    @PostMapping
    public Mono<ChatResponse> chat(@RequestBody Mono<ChatRequest> request) {
        return request.flatMap(chatService::handle);
    }
}
