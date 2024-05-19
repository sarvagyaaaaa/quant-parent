package org.dt.core.parent.controller;


import org.dt.core.parent.model.VulnerableFunctionalities;
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
    ResponseEntity<VulnerableFunctionalities> processUserQuery(@RequestBody String promptData, @PathVariable("dataId") String dataId){
        List<String> vulnerableHealthParameterIds = serviceIntelligenceService.processUserQuery(promptData, dataId);
        return ResponseEntity.ok(VulnerableFunctionalities.builder().healthCheckParamIds(vulnerableHealthParameterIds).build());
    }
}
