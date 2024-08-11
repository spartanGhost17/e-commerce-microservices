package com.ecommerce.product.dto;

import com.ecommerce.product.model.ProductImage;

import java.math.BigDecimal;
import java.util.List;

public record ProductPurchaseDto(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity,
        List<ProductImage> images
) {
}
