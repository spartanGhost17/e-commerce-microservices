package com.ecommerce.product.record;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "product is mandatory")
        Integer productId,
        @NotNull(message = "quantity is required")
        double quantity
) {
}
