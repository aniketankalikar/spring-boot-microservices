package com.advaya.microservices.orderservice.repositories;

import com.advaya.microservices.orderservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
