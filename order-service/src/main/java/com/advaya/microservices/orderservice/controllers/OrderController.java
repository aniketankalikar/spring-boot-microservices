package com.advaya.microservices.orderservice.controllers;

import com.advaya.microservices.orderservice.dtos.OrderRequest;
import com.advaya.microservices.orderservice.dtos.OrderResponse;
import com.advaya.microservices.orderservice.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/order")
@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/placeorder")
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest)
    {
        return orderService.placeOrder(orderRequest);
    }
}
