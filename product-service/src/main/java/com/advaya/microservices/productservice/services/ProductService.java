package com.advaya.microservices.productservice.services;

import com.advaya.microservices.productservice.dtos.ProductDTO;
import com.advaya.microservices.productservice.dtos.ProductRequest;
import com.advaya.microservices.productservice.dtos.ProductResponse;
import com.advaya.microservices.productservice.exceptions.ProductNotFoundException;
import com.advaya.microservices.productservice.models.Product;
import com.advaya.microservices.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public ProductResponse createProduct(ProductRequest productRequest)
    {

        ProductResponse productResponse = new ProductResponse();
        Product product = Product.builder().name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        product = productRepository.save(product);

        log.info("Product {} is saved "+product.getId());

        ProductDTO productDTO = ProductDTO.builder().name(product.getName())
                .description(product.getDescription()).price(product.getPrice()).id(product.getId()).build();

        productResponse.setProduct(productDTO);
        return productResponse;
    }

    public List<ProductDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();
        if(!CollectionUtils.isEmpty(products))
        {
            return products.stream().map(product -> mapEntityToDTO(product)).toList();
        }

        return null;
    }

    private ProductDTO mapEntityToDTO(Product product) {



        ProductDTO productDTO = ProductDTO.builder().name(product.getName())
                .description(product.getDescription()).price(product.getPrice()).id(product.getId()).build();

        return productDTO;
    }

    public ProductDTO getProductDetailsById(Long id, String token) {

        Optional<Product> optProduct = productRepository.findById(id);
        if(optProduct.isEmpty())
        {
            throw new ProductNotFoundException(id.toString());
        }

        Product product = optProduct.get();
        ProductDTO productDTO = ProductDTO.builder().name(product.getName())
                .description(product.getDescription()).price(product.getPrice()).id(product.getId()).build();

        return productDTO;
    }
}
