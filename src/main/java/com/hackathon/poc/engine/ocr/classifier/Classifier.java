package com.hackathon.poc.engine.ocr.classifier;

import com.hackathon.poc.engine.ocr.model.DocumentType;

import java.util.Map;

public interface Classifier {
    DocumentType classify(Map<Integer, String> contentMap);
}
