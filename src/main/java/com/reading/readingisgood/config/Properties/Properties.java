package com.reading.readingisgood.config.Properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class Properties {
    @Value("${reading-is-good-service.jwtSecret}")
    private String jwtSecret;
    @Value("${reading-is-good-service.jwtExpirationInMs}")
    private Long jwtExpirationInMs;
}