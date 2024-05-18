//package org.dt.core.parent.repository;
//
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.annotation.PostConstruct;
//import org.dt.core.parent.model.HealthCheckDAO;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.List;
//
//@Repository
//public interface SystemHealthCheckDataRepository extends JpaRepository<HealthCheckDAO, Long> {
//
//    @PostConstruct
//    public default void init() throws IOException {
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("staticData.json");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        StringBuilder stringBuilder = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            stringBuilder.append(line);
//        }
//        reader.close();
//        stringBuilder.toString();
//        TypeReference<List<HealthCheckDAO>> typeReference = new TypeReference<List<HealthCheckDAO>>(){} ;
//        List<HealthCheckDAO> healthCheckDAOS =  new ObjectMapper().readValue(stringBuilder.toString(),typeReference);
//        this.saveAll(healthCheckDAOS);
//    }
//}
