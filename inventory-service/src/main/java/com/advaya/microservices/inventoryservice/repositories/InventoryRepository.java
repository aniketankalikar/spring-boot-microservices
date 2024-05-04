package com.advaya.microservices.inventoryservice.repositories;

import com.advaya.microservices.inventoryservice.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    public List<Inventory> findBySkuCode(String skuCode);
}
