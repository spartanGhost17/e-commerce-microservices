package com.ecommerce.product.mapper;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.record.ProductRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .price(request.price())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .build();
    }
}
