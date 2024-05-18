package org.dt.core.parent.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.Duration;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = LLMConnectionConfig.PREFIX, ignoreUnknownFields = false)
@Scope("refresh")
@Getter
@Setter

public class LLMConnectionConfig {

    public static final String PREFIX = "llm";

    private String url;

    private Long waitBeforeFallbackTrigger;

    private Map<String, String> keywordToStaticIdMap;
}
