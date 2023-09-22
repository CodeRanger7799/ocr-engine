package com.hackathon.poc.engine.ocr.classifier;

import com.hackathon.poc.engine.ocr.model.DocumentType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Predicate;

@Component
public class KycClassifier implements Classifier {

    private static final Predicate<String> IS_PAN_CARD = concatenatedContent -> concatenatedContent.matches(".*TAX DEPARTMENT.*") && concatenatedContent.contains("GOVT. OF INDIA");

    @Override
    public DocumentType classify(Map<Integer, String> contentMap) {
        String concatenatedContent = concatenateMap(contentMap);
        if (IS_PAN_CARD.test(concatenatedContent)) {
            return DocumentType.PAN;
        }
        return DocumentType.NOT_DETECTED;

    }


    private String concatenateMap(Map<Integer, String> contentMap) {
        StringBuilder content = new StringBuilder();
        for (String values : contentMap.values()) {
            content.append(values).append(" ");
        }
        return content.toString();
    }
}
