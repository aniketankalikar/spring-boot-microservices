package com.advaya.microservices.productservice.controllers;

import com.advaya.microservices.productservice.dtos.ProductRequest;
import com.advaya.microservices.productservice.dtos.ProductResponse;
import com.advaya.microservices.productservice.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/product")
@RestController
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping("/create")
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest)
    {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts()
    {
        return productService.getAllProducts();

    }
}
