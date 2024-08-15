package com.ecommerce.order.dto;

import com.ecommerce.order.model.ProductImage;

import java.math.BigDecimal;
import java.util.List;

public record PurchaseDto(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        Double quantity,
        List<ProductImage> images
) {
}
