package com.advaya.microservices.orderservice.services;

import com.advaya.microservices.orderservice.dtos.InventoryResponse;
import com.advaya.microservices.orderservice.dtos.OrderLineItemsDTO;
import com.advaya.microservices.orderservice.dtos.OrderRequest;
import com.advaya.microservices.orderservice.dtos.OrderResponse;
import com.advaya.microservices.orderservice.models.Order;
import com.advaya.microservices.orderservice.models.OrderLineItems;
import com.advaya.microservices.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder().orderNo(UUID.randomUUID().toString()).build();
        List<OrderLineItemsDTO> orderLineItemsDTOList = orderRequest.getOrderLineItemsList();
        List<OrderLineItems> orderLineItems = orderLineItemsDTOList.stream().map(orderLineItemsDTO -> mapDTOToEntity(orderLineItemsDTO)).toList();
        order.setOrderLineItemsList(orderLineItems);

        // Invoke the Inventory Service and check for Stock before placing order. Use WebClient (Webflux)

        List<String> skuCodes =  order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:8082/api/inventory/isInStock", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock =  Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getIsInStock);

        OrderResponse response = null;
        if (allProductsInStock) {
            order = orderRepository.save(order);
            response = OrderResponse.builder().id(order.getId()).orderNo(order.getOrderNo()).build();
        } else {
            throw new IllegalArgumentException("Product Not Found in the Inventory");
        }
        return response;
    }

    private OrderLineItems mapDTOToEntity(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems = OrderLineItems.builder().skuCode(orderLineItemsDTO.getSkuCode())
                .quantity(orderLineItemsDTO.getQuantity()).price(orderLineItemsDTO.getPrice())
                .description(orderLineItemsDTO.getDescription())
                .build();
        return orderLineItems;
    }
}
