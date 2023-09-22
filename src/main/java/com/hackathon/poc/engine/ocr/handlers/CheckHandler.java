package com.hackathon.poc.engine.ocr.handlers;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.hackathon.poc.engine.ocr.service.CheckService;
import org.springframework.stereotype.Component;

@Component
public class CheckHandler implements ApiHandler {

    private final CheckService checkService;

    public CheckHandler(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent inputEvent) {
        return buildOkResponse(checkService.checkStatus());
    }


}
