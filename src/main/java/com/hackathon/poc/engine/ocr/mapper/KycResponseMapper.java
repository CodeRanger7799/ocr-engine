package com.hackathon.poc.engine.ocr.mapper;

import com.hackathon.poc.engine.ocr.model.DocumentType;
import com.hackathon.poc.engine.ocr.model.KycBO;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
public class KycResponseMapper {

    public KycBO mapResponse(DocumentType documentType, Map<Integer, String> contentMap) {
        switch (documentType) {
            case PAN:
                return mapPanResponse(contentMap);
            case AADHAR:
                return mapAadharResponse(contentMap);
            default:
                return mapUnDetectedResponse(contentMap);
        }
    }

    private KycBO mapAadharResponse(Map<Integer, String> contentMap) {
        KycBO kycBO = new KycBO();
        kycBO.setType(DocumentType.AADHAR.getDocumentType());
        kycBO.setIssuer(DocumentType.AADHAR.getIssuer());
        kycBO.setInfoDetected(new LinkedList<>(contentMap.values()));
        return kycBO;
    }

    private KycBO mapUnDetectedResponse(Map<Integer, String> contentMap) {
        KycBO kycBO = new KycBO();
        kycBO.setType(DocumentType.NOT_DETECTED.getDocumentType());
        kycBO.setIssuer(DocumentType.NOT_DETECTED.getIssuer());
        kycBO.setInfoDetected(new LinkedList<>(contentMap.values()));
        return kycBO;
    }

    private KycBO mapPanResponse(Map<Integer, String> contentMap) {
        KycBO kycBO = new KycBO();
        kycBO.setType(DocumentType.PAN.getDocumentType());
        kycBO.setIssuer(DocumentType.PAN.getIssuer());
        kycBO.setIdentifier(contentMap.getOrDefault(findKeyForValue(contentMap, ".*Account Number.*") + 1, null));
        kycBO.setInfoDetected(new LinkedList<>(contentMap.values()));
        return kycBO;
    }

    private Integer findKeyForValue(Map<Integer, String> contentMap, String matchingRegex) {
        for (Map.Entry<Integer, String> mapEntry : contentMap.entrySet()) {
            String value = mapEntry.getValue();
            if (value.matches(matchingRegex)) {
                return mapEntry.getKey();
            }
        }
        return -1;
    }

}
