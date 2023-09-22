package com.hackathon.poc.engine.ocr.service;

import com.hackathon.poc.engine.ocr.aws.TextractProcessor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VehicleClaimService {

    private final TextractProcessor textractProcessor;

    public VehicleClaimService(TextractProcessor textractProcessor) {
        this.textractProcessor = textractProcessor;
    }

    public Map<Integer, String> processVehicleClaimDoc(byte[] image) {
        return textractProcessor.analyzeImage(image);
    }
}
