package com.microservices.orderservice.client;

import com.microservices.orderservice.common.dto.InventoryResponse;
import com.microservices.orderservice.config.properties.InventoryServiceProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@RefreshScope
@Component
@RequiredArgsConstructor
public class InventoryServiceClient {
    private final InventoryServiceProperties inventoryServiceProperties;
    private final WebClient webClient;

    private static final String SKU_CODES = "skuCodes";


    public InventoryResponse[] inventoryResponse(List<String> skuCodes){
         String url = inventoryServiceProperties.getBaseUrl()+inventoryServiceProperties.getSubUrl();
        System.out.println("url: "+url);
        return webClient.get().uri(url,uriBuilder -> uriBuilder.queryParam(SKU_CODES,skuCodes).build()).retrieve().bodyToMono(InventoryResponse[].class).block();
    }

}
