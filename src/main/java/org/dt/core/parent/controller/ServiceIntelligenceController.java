package org.dt.core.parent.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.dt.core.parent.model.*;
import org.dt.core.parent.service.ServiceIntelligenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@RequestMapping("/intelligence")
@CrossOrigin("*")
public class ServiceIntelligenceController {

    @Autowired
    ServiceIntelligenceService serviceIntelligenceService;

    @PostMapping("/process/{dataId}")
    ResponseEntity<VulnerableFunctionalities> processUserQuery(@RequestBody UserPromptTO promptData, @PathVariable("dataId") String dataId){
        List<String> vulnerableHealthParameterIds = serviceIntelligenceService.processUserQuery(promptData.getPromptData(), dataId);
        return ResponseEntity.ok(VulnerableFunctionalities.builder().healthCheckParamIds(vulnerableHealthParameterIds).build());
    }

    @PostMapping("process/solution")
    ResponseEntity<Solutions> provideIntelligentSolution(@RequestBody DeviceTroubleshootDetails deviceTroubleshootDetails) throws JsonProcessingException {
        List<ParameterSpecificSolution> parameterSpecificSolutions = serviceIntelligenceService.fetchSmartSolutions(deviceTroubleshootDetails);
        return ResponseEntity.ok(Solutions.builder().parameterSpecificSolutions(parameterSpecificSolutions).build());
    }
}
