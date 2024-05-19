package com.advaya.microservices.productservice.controllers;

import com.advaya.microservices.productservice.dtos.ProductDTO;
import com.advaya.microservices.productservice.dtos.ProductRequest;
import com.advaya.microservices.productservice.dtos.ProductResponse;
import com.advaya.microservices.productservice.dtos.UserDTO;
import com.advaya.microservices.productservice.services.AuthCommons;
import com.advaya.microservices.productservice.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/product")
@RestController
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    private AuthCommons authCommons;

    @PostMapping("/create")
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest)
    {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts()
    {
        return productService.getAllProducts();

    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO>  getProductDetailsById(@PathVariable Long id, @RequestHeader("authToken") String token)
    {

        UserDTO userDTO = authCommons.validateToken(token);
        if(userDTO == null)
        {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        ProductDTO productDTO = productService.getProductDetailsById(id, token);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);

    }


}
