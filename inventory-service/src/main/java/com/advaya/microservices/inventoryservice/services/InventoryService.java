package com.advaya.microservices.inventoryservice.services;

import com.advaya.microservices.inventoryservice.dtos.InventoryResponse;
import com.advaya.microservices.inventoryservice.models.Inventory;
import com.advaya.microservices.inventoryservice.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode)
    {
        List<Inventory> inventoryList =  inventoryRepository.findBySkuCodeIn(skuCode);
        return inventoryList.stream().map(inventory -> InventoryResponse.builder().skuCode(inventory.getSkuCode())
                                                    .quantity(inventory.getQuantity())
                                                    .isInStock(inventory.getQuantity()>0).build()
                                      ).toList();

    }
}
