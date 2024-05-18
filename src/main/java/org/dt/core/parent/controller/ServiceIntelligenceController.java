package org.dt.core.parent.controller;


import org.dt.core.parent.model.VulnerableFunctionalities;
import org.dt.core.parent.service.ServiceIntelligenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intelligence")
@CrossOrigin("*")
public class ServiceIntelligenceController {

    @Autowired
    ServiceIntelligenceService serviceIntelligenceService;

    @PostMapping
    ResponseEntity<VulnerableFunctionalities> processUserQuery(@RequestBody String promptData){
        List<String> vulnerableHealthParameterIds = serviceIntelligenceService.processUserQuery(promptData);
        return ResponseEntity.ok(VulnerableFunctionalities.builder().healthCheckParamIds(vulnerableHealthParameterIds).build());
    }
}
