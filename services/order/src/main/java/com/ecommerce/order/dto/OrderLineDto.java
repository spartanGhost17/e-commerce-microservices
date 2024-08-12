package com.ecommerce.order.dto;

public record OrderLineDto(
        Integer id,
        Integer productId,
        double quantity
) {
}
