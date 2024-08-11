package com.ecommerce.product.record;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
    Integer id,
    @NotNull(message = "Product name is required")
    String name,
    @NotNull(message = "Product description is required")
    String description,
    @Positive(message = "Available quantity cannot be negative")
    double availableQuantity,
    @Positive(message = "Price cannot be negative")
    BigDecimal price,
    @NotNull(message = "Product category is required")
    Integer categoryId
) {
}
