package com.bank.intent_check_svc.utils;

import com.bank.intent_check_svc.intent.IntentHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IntentHandlerFactory {

    private final Map<ChatIntent, IntentHandler> handlers = new HashMap<>();

    public IntentHandlerFactory(List<IntentHandler> handlerList) {
        for (IntentHandler handler : handlerList) {
            handlers.put(handler.intent(), handler);
        }
    }

    public IntentHandler getHandler(ChatIntent intent) {
        return handlers.getOrDefault(intent, handlers.get(ChatIntent.UNKNOWN));
    }
}
