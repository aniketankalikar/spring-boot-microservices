package com.advaya.microservices.orderservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDTO implements Serializable {

    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
    private String description;
}
