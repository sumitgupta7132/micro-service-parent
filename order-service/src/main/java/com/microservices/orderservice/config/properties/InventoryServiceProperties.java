package com.microservices.orderservice.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Data
@Configuration
@ConfigurationProperties(prefix = "inventory-service")
public class InventoryServiceProperties {
    private String baseUrl;
    private String subUrl;
}
