package com.advaya.microservices.inventoryservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse implements Serializable {

    private String skuCode;
    private Integer quantity;
    private Boolean isInStock;
}
