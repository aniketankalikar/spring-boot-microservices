package com.advaya.microservices.inventoryservice;

import com.advaya.microservices.inventoryservice.models.Inventory;
import com.advaya.microservices.inventoryservice.repositories.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository)
	{

		inventoryRepository.deleteAll();
		return args -> {

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone-13");
			inventory1.setQuantity(100);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iphone-13-red");
			inventory2.setQuantity(10);

			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);

		};
	}

}
