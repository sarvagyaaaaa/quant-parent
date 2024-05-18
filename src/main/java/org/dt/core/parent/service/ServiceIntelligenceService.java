package org.dt.core.parent.service;

import org.dt.core.parent.config.LLMConnectionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ServiceIntelligenceService {

    @Autowired
    LLMConnectionConfig llmConnectionConfig;

    public List<String> processUserQuery(String promptData) {
        RestTemplate template = new RestTemplate();
        String url = llmConnectionConfig.getUrl();
        HttpEntity<String> httpEntity = new HttpEntity<>("");
        ResponseEntity<String> stringResponseEntity = template.postForEntity(url, httpEntity, String.class);
        String[] keywords = stringResponseEntity.getBody().split("_");
        List<String> healthCheckIds = Arrays.asList(keywords).stream().map(e->llmConnectionConfig.getKeywordToStaticIdMap().get(e)).toList();
        return healthCheckIds;
    }
}
