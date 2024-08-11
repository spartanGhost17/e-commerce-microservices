package com.ecommerce.product.dto;

import com.ecommerce.product.model.Category;
import com.ecommerce.product.model.ProductImage;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(
        Integer id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        String serialNumber,
        Category category,
        List<ProductImage> images
) {
}
