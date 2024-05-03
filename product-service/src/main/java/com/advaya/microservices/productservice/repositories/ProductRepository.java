package com.advaya.microservices.productservice.repositories;

import com.advaya.microservices.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
