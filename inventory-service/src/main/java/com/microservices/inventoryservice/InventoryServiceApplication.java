package com.microservices.inventoryservice;

import com.microservices.inventoryservice.model.Inventory;
import com.microservices.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory iphoneWhiteInventory = new Inventory();
			iphoneWhiteInventory.setSkuCode("iphone_white");
			iphoneWhiteInventory.setQuantity(3);
			Inventory iphoneRedInventory = new Inventory();
			iphoneRedInventory.setSkuCode("iphone_red");
			iphoneRedInventory.setQuantity(1);
			inventoryRepository.save(iphoneWhiteInventory);
			inventoryRepository.save(iphoneRedInventory);
		};
	}

}
