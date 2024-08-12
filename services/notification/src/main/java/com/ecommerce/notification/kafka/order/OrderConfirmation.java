package com.ecommerce.notification.kafka.order;

import com.ecommerce.notification.enums.PaymentMethod;

import java.math.BigDecimal;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
