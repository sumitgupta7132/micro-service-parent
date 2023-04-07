package com.microservices.inventoryservice.rest.controller;

import com.microservices.inventoryservice.common.dto.InventoryResponse;
import com.microservices.inventoryservice.rest.response.HttpApiResponse;
import com.microservices.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public HttpApiResponse<List<InventoryResponse>> isInStock(@RequestParam(name="skuCodes") List<String> skuCodes){
         List<InventoryResponse> inventoryResponse = inventoryService.isPresentInInventory(skuCodes);
        return new HttpApiResponse<>(inventoryResponse);
    }
}
