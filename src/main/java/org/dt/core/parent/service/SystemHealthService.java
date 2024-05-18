package org.dt.core.parent.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dt.core.parent.model.HealthCheckDAO;
import org.dt.core.parent.model.HealthCheckTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Component
public class SystemHealthService {
    static List<HealthCheckDAO> healthCheckTOS() throws JsonProcessingException {
        InputStream inputStream = SystemHealthService.class.getClassLoader().getResourceAsStream("staticData.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stringBuilder.append(line);
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stringBuilder.toString();
        TypeReference<List<HealthCheckDAO>> typeReference = new TypeReference<List<HealthCheckDAO>>(){} ;
        return new ObjectMapper().readValue(stringBuilder.toString(), typeReference);
    }


    public List<HealthCheckTO> fetchAllHealthCheckParameterResponse(String dataId) throws JsonProcessingException {
        List<HealthCheckDAO> healthCheckDAOS =  (SystemHealthService.healthCheckTOS());
        return healthCheckDAOS.stream().filter(e-> Objects.equals(e.getName(),dataId)).findFirst().get().getStaticHealthCheckResults();
    }

    private List<HealthCheckTO> convertToHealthCheckTo(List<String> healthCheckParameterJson){
        return healthCheckParameterJson.stream().map(e-> {
            try {
                return new ObjectMapper().readValue(e, HealthCheckTO.class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(Collectors.toList());
    }

}
