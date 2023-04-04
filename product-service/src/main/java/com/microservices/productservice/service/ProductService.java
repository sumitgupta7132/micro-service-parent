package com.microservices.productservice.service;

import com.microservices.productservice.common.dto.ProductRequest;
import com.microservices.productservice.common.dto.ProductResponse;
import com.microservices.productservice.model.Product;
import com.microservices.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductRequest createProduct(ProductRequest productRequest) {
        log.info("Request received for creating product={}", productRequest);
        Product product = saveProductToDb(productRequest.getName(), productRequest.getDescription(), productRequest.getPrice());
        log.info("Product={} stored in DB", product);
        return createProductRequestDto(product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToProductResponse).toList();
    }

    private ProductRequest createProductRequestDto(Product product) {
        return ProductRequest.builder().name(product.getName()).description(product.getDescription()).price(product.getPrice()).build();
    }

    private Product saveProductToDb(String name, String description, BigDecimal price) {
        Product product = createProductModel(name, description, price);
        return productRepository.save(product);
    }

    private Product createProductModel(String name, String description, BigDecimal price) {
        return Product.builder().name(name).description(description).price(price).build();
    }


    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().name(product.getName()).description(product.getDescription()).price(product.getPrice()).build();
    }
}
