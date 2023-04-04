package com.microservices.productservice.rest.controller;

import com.microservices.productservice.common.dto.ProductResponse;
import com.microservices.productservice.common.dto.ProductRequest;
import com.microservices.productservice.rest.response.HttpApiResponse;
import com.microservices.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HttpApiResponse<ProductRequest> createProduct(@RequestBody ProductRequest productRequest){
        ProductRequest createProductResponse = productService.createProduct(productRequest);
        return new HttpApiResponse<>(createProductResponse);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public HttpApiResponse<List<ProductResponse>> getProductList(){
        List<ProductResponse> products = productService.getAllProducts();
        return new HttpApiResponse<>(products);
    }
}
