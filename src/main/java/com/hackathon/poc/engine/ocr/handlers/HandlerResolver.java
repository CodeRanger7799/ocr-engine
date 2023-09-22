package com.hackathon.poc.engine.ocr.handlers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Logger;

@Component
public class HandlerResolver {

    private static final Logger LOGGER = Logger.getLogger("HandlerResolver");

    private final Map<String, ApiHandler> handlerMap;

    public HandlerResolver(@Qualifier("handlerMap") Map<String, ApiHandler> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public ApiHandler getHandler(String handlerName) {
        return handlerMap.get(handlerName);
    }
}
