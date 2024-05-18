package org.dt.core.parent.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Slf4j
@Getter
public class HealthCheckDAO {
    private String id;

    private String name;

    private List<HealthCheckTO> staticHealthCheckResults = new ArrayList<>();

//    public void addHealthCheckResults(List<HealthCheckTO> healthCheckTOS) {
//        this.staticHealthCheckResults.addAll(healthCheckTOS.stream().map(e-> {
//            try {
//                return new ObjectMapper().writeValueAsString(e);
//            } catch (JsonProcessingException ex) {
//                throw new RuntimeException(ex);
//            }
//        }).collect(Collectors.toList()));
//    }

}
