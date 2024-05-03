package com.advaya.microservices.productservice.services;

import com.advaya.microservices.productservice.dtos.ProductRequest;
import com.advaya.microservices.productservice.dtos.ProductResponse;
import com.advaya.microservices.productservice.models.Product;
import com.advaya.microservices.productservice.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public ProductResponse createProduct(ProductRequest productRequest)
    {
        Product product = Product.builder().name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        product = productRepository.save(product);

        log.info("Product {} is saved "+product.getId());

        ProductResponse productResponse = ProductResponse.builder().name(product.getName())
                .description(product.getDescription()).price(product.getPrice()).id(product.getId()).build();

        return productResponse;
    }

    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();
        if(!CollectionUtils.isEmpty(products))
        {
            return products.stream().map(product -> mapEntityToDTO(product)).toList();
        }

        return null;
    }

    private ProductResponse mapEntityToDTO(Product product) {

        ProductResponse productResponse = ProductResponse.builder().name(product.getName())
                .description(product.getDescription()).price(product.getPrice()).id(product.getId()).build();

        return productResponse;
    }
}
