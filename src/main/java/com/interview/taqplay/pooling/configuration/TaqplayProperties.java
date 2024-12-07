package com.interview.taqplay.pooling.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties("taqplay")
public class TaqplayProperties {
    private UrlProperties url;
}
