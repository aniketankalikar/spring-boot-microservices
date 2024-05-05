package com.advaya.microservices.inventoryservice.controllers;

import com.advaya.microservices.inventoryservice.dtos.InventoryResponse;
import com.advaya.microservices.inventoryservice.services.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/inventory")
@RestController
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @GetMapping("/isInStock")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam("skuCode")List<String> skuCode)
    {
        return inventoryService.isInStock(skuCode);
    }
}
