package com.microservices.inventoryservice.service;

import com.microservices.inventoryservice.common.dto.InventoryResponse;
import com.microservices.inventoryservice.model.Inventory;
import com.microservices.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional
    public List<InventoryResponse> isPresentInInventory(final List<String> skuCodes){
        List<Inventory> inventories = inventoryRepository.findBySkuCodeIn(skuCodes);
        return inventories.stream().map(this::mapToInventoryResponse).toList();
    }

    private InventoryResponse mapToInventoryResponse(Inventory inventory){
        return InventoryResponse.builder().skuCode(inventory.getSkuCode()).isInStock(inventory.getQuantity()>0).build();
    }

}
