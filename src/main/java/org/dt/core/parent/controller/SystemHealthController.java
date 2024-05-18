package org.dt.core.parent.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.dt.core.parent.model.HealthCheckResponse;
import org.dt.core.parent.model.HealthCheckTO;
import org.dt.core.parent.service.SystemHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system")
@CrossOrigin("*")
@Component
@Slf4j
public class SystemHealthController {

    @Autowired
    SystemHealthService systemHealthService;

    @GetMapping
    public ResponseEntity<HealthCheckResponse> getHealthCheckResponse(@RequestParam("dataId") String dataId) throws JsonProcessingException {
        List<HealthCheckTO> healthCheckTOS = systemHealthService.fetchAllHealthCheckParameterResponse(dataId);
        return ResponseEntity.ok(HealthCheckResponse.builder().healthCheckParameters(healthCheckTOS).build());
    }

//    @PostMapping("/health/check")
//    public ResponseEntity<>

}
