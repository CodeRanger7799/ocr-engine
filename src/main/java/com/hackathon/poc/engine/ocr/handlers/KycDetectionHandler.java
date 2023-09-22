package com.hackathon.poc.engine.ocr.handlers;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.hackathon.poc.engine.ocr.service.KycDetectionService;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class KycDetectionHandler implements ApiHandler {

    private final KycDetectionService kycDetectionService;

    public KycDetectionHandler(KycDetectionService kycDetectionService) {
        this.kycDetectionService = kycDetectionService;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent inputEvent) {
        return buildOkResponse(kycDetectionService.performKycDetection(Base64Utils.decodeFromString(inputEvent.getBody())));
    }
}
