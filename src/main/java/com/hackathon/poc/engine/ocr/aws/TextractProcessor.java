package com.hackathon.poc.engine.ocr.aws;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.*;

import java.io.ByteArrayInputStream;
import java.util.*;

@Component
public class TextractProcessor {

    private final TextractClient textractClient;

    public TextractProcessor(TextractClient textractClient) {
        this.textractClient = textractClient;
    }


    public Map<Integer, String> analyzeImage(byte[] imageByte) {
        Map<Integer, String> contentMap = new LinkedHashMap<>();
        SdkBytes sourceBytes = SdkBytes.fromInputStream(new ByteArrayInputStream(imageByte));
        Document myDoc = Document.builder()
                .bytes(sourceBytes)
                .build();

        List<FeatureType> featureTypes = new ArrayList<FeatureType>();
        featureTypes.add(FeatureType.FORMS);
        featureTypes.add(FeatureType.TABLES);

        AnalyzeDocumentRequest analyzeDocumentRequest = AnalyzeDocumentRequest.builder()
                .featureTypes(featureTypes)
                .document(myDoc)
                .build();

        AnalyzeDocumentResponse analyzeDocument = textractClient.analyzeDocument(analyzeDocumentRequest);
        List<Block> docInfo = analyzeDocument.blocks();
        Iterator<Block> blockIterator = docInfo.iterator();

        int lineCounter = 1;

        while (blockIterator.hasNext()) {
            Block block = blockIterator.next();
            if (block.blockType() == BlockType.LINE) {
                contentMap.put(lineCounter, block.text());
                ++lineCounter;
            }
        }

        return contentMap;
    }
}
