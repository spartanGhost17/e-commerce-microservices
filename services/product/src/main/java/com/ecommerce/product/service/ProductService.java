package com.ecommerce.product.service;

import com.ecommerce.product.dto.ProductDto;
import com.ecommerce.product.dto.ProductPurchaseDto;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.record.ProductPurchaseRequest;
import com.ecommerce.product.record.ProductRequest;
import com.ecommerce.product.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto createProduct(ProductRequest request) {
        log.info("Creating product {}", request);
        var product = productMapper.toProduct(request);
        return null;
    }

    public List<ProductPurchaseDto> purchase(@Valid List<ProductPurchaseRequest> requests) {
        log.info("purchasing product");
        return null;
    }

    public ProductDto findById(String productId) {
        return null;
    }

    public List<ProductDto> findAll() {
        return null;
    }
}
