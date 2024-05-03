package com.advaya.microservices.orderservice.services;

import com.advaya.microservices.orderservice.dtos.OrderLineItemsDTO;
import com.advaya.microservices.orderservice.dtos.OrderRequest;
import com.advaya.microservices.orderservice.dtos.OrderResponse;
import com.advaya.microservices.orderservice.models.Order;
import com.advaya.microservices.orderservice.models.OrderLineItems;
import com.advaya.microservices.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse placeOrder(OrderRequest orderRequest)
    {
        Order order = Order.builder().orderNo(UUID.randomUUID().toString()).build();
        List<OrderLineItemsDTO> orderLineItemsDTOList = orderRequest.getOrderLineItemsList();
        List<OrderLineItems> orderLineItems =  orderLineItemsDTOList.stream().map(orderLineItemsDTO -> mapDTOToEntity(orderLineItemsDTO)).toList();
        order.setOrderLineItemsList(orderLineItems);

        order = orderRepository.save(order);
        OrderResponse response = OrderResponse.builder().id(order.getId()).orderNo(order.getOrderNo()).build();

        return response;
    }

    private OrderLineItems mapDTOToEntity(OrderLineItemsDTO orderLineItemsDTO)
    {
        OrderLineItems orderLineItems  = OrderLineItems.builder().skuCode(orderLineItemsDTO.getSkuCode())
                                         .quantity(orderLineItemsDTO.getQuantity()).price(orderLineItemsDTO.getPrice())
                                          .description(orderLineItemsDTO.getDescription())
                                         .build();
        return orderLineItems;
    }
}
