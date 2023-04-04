package com.microservices.inventoryservice.rest.controller;

import com.microservices.inventoryservice.rest.response.HttpApiResponse;
import com.microservices.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public HttpApiResponse<Boolean> isInStock(@PathVariable(name="skuCode") String skuCode){
        Boolean isPresentInInventory = inventoryService.isPresentInInventory(skuCode);
        return new HttpApiResponse<>(isPresentInInventory);
    }
}
