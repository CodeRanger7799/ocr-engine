package com.hackathon.poc.engine.ocr;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.hackathon.poc.engine.ocr.handlers.ApiHandler;
import com.hackathon.poc.engine.ocr.handlers.HandlerResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

//org.springframework.cloud.function.adapter.aws.FunctionInvoker
///ocrEngine
@Component
public class OcrEngine implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Autowired
    private HandlerResolver handlerResolver;

    @Override
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent inputEvent) {
        String path = inputEvent.getPath();
        ApiHandler handler = handlerResolver.getHandler(path);
        return handler != null ? handler.handle(inputEvent) : buildHandlerNotFoundResponse(path);
    }

    private APIGatewayProxyResponseEvent buildHandlerNotFoundResponse(String path) {
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setStatusCode(422);
        responseEvent.setBody("No handler found for the specified name: " + path);
        return responseEvent;
    }


}
