package com.ecommerce.product.controller;


import com.ecommerce.product.dto.ProductDto;
import com.ecommerce.product.model.HttpResponse;
import com.ecommerce.product.record.ProductPurchaseRequest;
import com.ecommerce.product.record.ProductRequest;
import com.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<HttpResponse> createProduct(@RequestBody @Valid ProductRequest request) {

        return new ResponseEntity<>(HttpResponse.builder()
                .message("Product created")
                .data(Map.of("product", productService.createProduct(request)))
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/purchase")
    public ResponseEntity<HttpResponse> purchaseProduct(@RequestBody @Valid List<ProductPurchaseRequest> requests) {

        return new ResponseEntity<>(HttpResponse.builder()
                .message("Product purchased")
                .data(Map.of("products", productService.purchase(requests)))
                .build(), HttpStatus.OK);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<HttpResponse> findProductById(@PathVariable("productId") String productId) {
        return new ResponseEntity<>(HttpResponse.builder()
                .message("Product found by Id")
                .data(Map.of("product", productService.findById(productId)))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<HttpResponse> findAllProducts() {
        return new ResponseEntity<>(HttpResponse.builder()
                .message("All products")
                .data(Map.of("products", productService.findAll()))
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }
}
