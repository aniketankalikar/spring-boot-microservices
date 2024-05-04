package com.advaya.microservices.inventoryservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String skuCode;
    private Integer quantity;
}
