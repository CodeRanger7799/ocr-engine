package com.hackathon.poc.engine.ocr.model;

public enum DocumentType {
    PAN("PAN", "GOVT. OF INDIA"),
    AADHAR("Aadhar", "GOVT. OF INDIA"),
    NOT_DETECTED("NOT_DETECTED", "NOT_DETECTED");

    private final String documentType;

    private final String issuer;

    DocumentType(String documentType, String issuer) {
        this.documentType = documentType;
        this.issuer = issuer;
    }

    @Override
    public String toString() {
        return "DocumentType{" +
                "documentType='" + documentType + '\'' +
                ", issuer='" + issuer + '\'' +
                '}';
    }

    public String getDocumentType() {
        return documentType;
    }
    public String getIssuer() {
        return issuer;
    }


}
