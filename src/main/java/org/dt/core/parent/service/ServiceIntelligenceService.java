package org.dt.core.parent.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.dt.core.parent.config.FallbackConfig;
import org.dt.core.parent.config.LLMConnectionConfig;
import org.dt.core.parent.model.DeviceTroubleshootDetails;
import org.dt.core.parent.model.HealthCheckTO;
import org.dt.core.parent.model.ParameterSpecificSolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
@Slf4j
@Component
public class ServiceIntelligenceService {

    @Autowired
    LLMConnectionConfig llmConnectionConfig;

    @Autowired
    FallbackConfig fallbackConfig;

    public List<String> processUserQuery(String promptData, String dataId) {
        log.info("Fallback:: {}", fallbackConfig.getDataIdToIntelligenceFallbackResponseMap());
        log.info("llm:: {}", llmConnectionConfig.getKeywordToStaticIdMap());

        RestTemplate template = createRestTemplateWithTimeout(llmConnectionConfig.getWaitBeforeFallbackTrigger()); // 5000 milliseconds = 5 seconds
        String url = llmConnectionConfig.getUrl();
        HttpEntity<String> httpEntity = new HttpEntity<>(promptData);

        CompletableFuture<ResponseEntity<String>> future = CompletableFuture.supplyAsync(() ->
                template.postForEntity(url, httpEntity, String.class)
        );

        String[] keywords;
        try {
            ResponseEntity<String> stringResponseEntity = future.get(5, TimeUnit.SECONDS);
            keywords = stringResponseEntity.getBody().split("_");
            if (stringResponseEntity == null || stringResponseEntity.getBody() == null) {
                throw new TimeoutException("No response from API");
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            return fallbackConfig.getDataIdToIntelligenceFallbackResponseMap().get(dataId);
        }

        return Arrays.stream(keywords)
                .map(e -> llmConnectionConfig.getKeywordToStaticIdMap().get(e))
                .toList();
    }

    private RestTemplate createRestTemplateWithTimeout(int timeout) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(timeout);
        factory.setReadTimeout(timeout);
        return new RestTemplate(factory);
    }

    public List<ParameterSpecificSolution> fetchSmartSolutions(DeviceTroubleshootDetails deviceTroubleshootDetails) throws JsonProcessingException {
        RestTemplate template = createRestTemplateWithTimeout(llmConnectionConfig.getWaitBeforeFallbackTrigger()); // 5000 milliseconds = 5 seconds
        String url = llmConnectionConfig.getUrl();
        List<String> impactedParameters = SystemHealthService.healthCheckTOS().stream().filter(e->deviceTroubleshootDetails.getPriorityToImpactedParameterIds().containsValue(e)).map(e->e.getName()).toList();
        StringBuilder stringBuilder = new StringBuilder("");
        impactedParameters.forEach(e-> stringBuilder.append(e).append("_"));
        HttpEntity<String> httpEntity = new HttpEntity<>(stringBuilder.toString());

        CompletableFuture<ResponseEntity<String>> future = CompletableFuture.supplyAsync(() ->
                template.postForEntity(url, httpEntity, String.class)
        );
        List<ParameterSpecificSolution> list;
        try {
            ResponseEntity<String> stringResponseEntity = future.get(5, TimeUnit.SECONDS);
            List<String> solutions = Arrays.stream(stringResponseEntity.getBody().split("_")).toList();
            list = deviceTroubleshootDetails.getPriorityToImpactedParameterIds().values().stream().map(i-> ParameterSpecificSolution.builder().name(i).action(fallbackConfig.getParamToSolutionMap().get(i)).build()).collect(Collectors.toList()); ;
            if (stringResponseEntity == null || stringResponseEntity.getBody() == null) {
                throw new TimeoutException("No response from API");
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            list = deviceTroubleshootDetails.getPriorityToImpactedParameterIds().values().stream().map(i-> ParameterSpecificSolution.builder().name(i).action(fallbackConfig.getParamToSolutionMap().get(i)).build()).collect(Collectors.toList()); ;
        }

            return list;

    }
}
