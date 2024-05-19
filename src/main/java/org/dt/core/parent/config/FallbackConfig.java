package org.dt.core.parent.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = FallbackConfig.PREFIX, ignoreUnknownFields = false)
//@Scope("refresh")
@Getter
@Setter

public class FallbackConfig {
    public static final String PREFIX = "fallback";

    private Map<String, List<String>> dataIdToIntelligenceFallbackResponseMap;

    private Map<String, String> paramToSolutionMap;
}
