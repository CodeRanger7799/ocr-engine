package com.hackathon.poc.engine.ocr.handlers;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;

public interface ApiHandler {

    APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent inputEvent);

    default APIGatewayProxyResponseEvent buildOkResponse(Object obj) {
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setStatusCode(200);
        responseEvent.setBody(new Gson().toJson(obj));
        return responseEvent;
    }
}
