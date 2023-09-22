package com.hackathon.poc.engine.ocr.service;

import com.hackathon.poc.engine.ocr.model.CheckBO;
import org.springframework.stereotype.Service;

@Service
public class CheckService {
    public CheckBO checkStatus() {
        CheckBO checkBO = new CheckBO();
        checkBO.setApplication("OCR Engine");
        checkBO.setLevel("SHALLOW");
        checkBO.setMessage("Hello From IT!!");
        return checkBO;
    }
}
