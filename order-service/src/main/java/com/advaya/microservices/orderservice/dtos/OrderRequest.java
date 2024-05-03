package com.advaya.microservices.orderservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest implements Serializable {

    private String orderNo;

    private List<OrderLineItemsDTO> orderLineItemsList;
}
