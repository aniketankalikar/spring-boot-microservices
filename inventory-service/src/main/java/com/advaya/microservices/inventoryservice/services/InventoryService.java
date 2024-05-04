package com.advaya.microservices.inventoryservice.services;

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
    public Boolean isInStock(String skuCode)
    {
        List<Inventory> inventoryList =  inventoryRepository.findBySkuCode(skuCode);
        return !CollectionUtils.isEmpty(inventoryList) ? Boolean.TRUE : Boolean.FALSE;
    }
}
