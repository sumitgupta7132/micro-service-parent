package com.microservices.inventoryservice.service;

import com.microservices.inventoryservice.model.Inventory;
import com.microservices.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional
    public Boolean isPresentInInventory(final String skuCode){
        Optional<Inventory> inventory = inventoryRepository.findBySkuCode(skuCode);
        return inventory.isPresent();
    }

}
