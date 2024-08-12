package com.ecommerce.order.record;

import com.ecommerce.order.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be selected")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer Id cannot be null")
        @NotEmpty(message = "Customer Id cannot be null")
        @NotBlank(message = "Customer Id cannot be null")
        String customerId,
        @NotEmpty(message = "You should purchase at minimum 1 product")
        List<PurchaseRequest> products
) {
}
