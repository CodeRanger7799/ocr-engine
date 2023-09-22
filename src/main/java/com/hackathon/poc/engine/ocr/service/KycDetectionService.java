package com.hackathon.poc.engine.ocr.service;

import com.hackathon.poc.engine.ocr.aws.TextractProcessor;
import com.hackathon.poc.engine.ocr.classifier.KycClassifier;
import com.hackathon.poc.engine.ocr.mapper.KycResponseMapper;
import com.hackathon.poc.engine.ocr.model.KycBO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KycDetectionService {

    private final KycClassifier kycClassifier;
    private final KycResponseMapper kycResponseMapper;
    private final TextractProcessor textractProcessor;


    public KycDetectionService(KycClassifier kycClassifier, KycResponseMapper kycResponseMapper, TextractProcessor textractProcessor) {
        this.kycClassifier = kycClassifier;
        this.kycResponseMapper = kycResponseMapper;
        this.textractProcessor = textractProcessor;
    }

    public KycBO performKycDetection(byte[] image) {
        Map<Integer, String> detectedContent = textractProcessor.analyzeImage(image);
        return kycResponseMapper.mapResponse(kycClassifier.classify(detectedContent), detectedContent);
    }
}
