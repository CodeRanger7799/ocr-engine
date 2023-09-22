package com.hackathon.poc.engine.ocr.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class KycBO {
    private String type;
    private String identifier;
    private String issuer;
    private List<String> infoDetected;
}
