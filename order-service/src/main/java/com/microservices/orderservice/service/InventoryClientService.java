package com.microservices.orderservice.service;

import com.microservices.orderservice.client.InventoryServiceClient;
import com.microservices.orderservice.common.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InventoryClientService {
    private final InventoryServiceClient inventoryServiceClient;

    public Boolean isPresentInInventory(List<String> skuCodes){
        InventoryResponse[] inventoryResponses = inventoryServiceClient.inventoryResponse(skuCodes);
        System.out.println(inventoryResponses);
        return Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getIsInStock);
    }

}
