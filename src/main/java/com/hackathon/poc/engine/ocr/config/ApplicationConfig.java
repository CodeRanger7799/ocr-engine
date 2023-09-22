package com.hackathon.poc.engine.ocr.config;

import com.hackathon.poc.engine.ocr.handlers.ApiHandler;
import com.hackathon.poc.engine.ocr.handlers.CheckHandler;
import com.hackathon.poc.engine.ocr.handlers.VehicleClaimHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApplicationConfig {

    @Bean("handlerMap")
    public Map<String, ApiHandler> handlerMap(CheckHandler checkHandler, VehicleClaimHandler vehicleClaimHandler) {
        Map<String, ApiHandler> handlerMap = new HashMap<>();
        handlerMap.put("/ocrEngine/check", checkHandler);
        handlerMap.put("/ocrEngine/claim/vehicle", vehicleClaimHandler);
        return handlerMap;
    }
}
