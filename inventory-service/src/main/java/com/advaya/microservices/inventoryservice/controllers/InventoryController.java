package com.advaya.microservices.inventoryservice.controllers;

import com.advaya.microservices.inventoryservice.services.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/inventory")
@RestController
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean isInStock(@PathVariable("skuCode") String skuCode)
    {
        return inventoryService.isInStock(skuCode);
    }
}
