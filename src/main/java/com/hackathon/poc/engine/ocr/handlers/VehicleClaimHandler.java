package com.hackathon.poc.engine.ocr.handlers;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.hackathon.poc.engine.ocr.service.VehicleClaimService;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class VehicleClaimHandler implements ApiHandler {

    private final VehicleClaimService vehicleClaimService;

    public VehicleClaimHandler(VehicleClaimService vehicleClaimService) {
        this.vehicleClaimService = vehicleClaimService;
    }

    @Override
    public APIGatewayProxyResponseEvent handle(APIGatewayProxyRequestEvent inputEvent) {
        return buildOkResponse(vehicleClaimService.processVehicleClaimDoc(Base64Utils.decodeFromString(inputEvent.getBody())));
    }
}
